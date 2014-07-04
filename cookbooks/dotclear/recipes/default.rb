package 'apache2' do
  action :install
end

service 'apache2' do
  action [ :enable, :start ]
end

package 'php5'
package 'libapache2-mod-php5'
package 'mysql-server'
package 'php5-mysql'

cookbook_file 'index.php' do
	path "/var/www/index.php"
	action :create
end

dotclear_latest = "#{Chef::Config[:file_cache_path]}/dotclear.tar.gz"

cookbook_file "dotclear-2.6.3.tar.gz" do
  path dotclear_latest
  mode "0644"
end

execute "untar-dotclear" do
	cwd "/var/www/"
	command "tar --strip-components 1 -xzf " + dotclear_latest
end

directory "/var/www/dotclear/cache" do
	mode "0776"
end

directory "/var/www/dotclear/public" do
	mode "0776"
end

execute "create-dotclear-database" do
	command "mysqladmin -uroot create dotclear"
	not_if { FileTest.exist?("/var/www/dotclear/inc/config.php") }
end

cookbook_file 'config.php' do
	path "/var/www/dotclear/inc/config.php"
	action :create_if_missing
end


