execute "echo 'grub-pc hold' | dpkg --set-selections" do 
	not_if { ::File.directory?("/home/formation/workspace/selenium-workshop")}
end

execute 'apt-get update' do
	not_if { ::File.directory?("/home/formation/workspace/selenium-workshop")}
end

execute 'apt-get -y upgrade' do
	not_if { ::File.directory?("/home/formation/workspace/selenium-workshop")}
end

directory "/var/www" do
  owner "root"
  group "root"
  mode 00777
  action :create
end

cookbook_file 'index.html' do
	path "/var/www/index.html"
	action :create
end

cookbook_file 'bootstrap.min.css' do
	path "/var/www/bootstrap.min.css"
	action :create
end


cookbook_file 'jquery-2.1.1.min.js' do
	path "/var/www/jquery-2.1.1.min.js"
	action :create
end

directory "/home/formation/workspace" do
  owner "formation"
  group "formation"
  mode 00777
  action :create
end

execute "git clone https://github.com/dwursteisen/selenium-workshop.git" do
	cwd "/home/formation/workspace"
	user "formation"
	group "formation"
	not_if { ::File.directory?("/home/formation/workspace/selenium-workshop")}
end
