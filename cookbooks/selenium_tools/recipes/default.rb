

remote_file "/home/formation/phantomjs.tar.bz2" do
  source "https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-1.9.7-linux-x86_64.tar.bz2"
  action :create
  owner "formation"
  group "formation"
  not_if { ::File.directory?("/home/formation/phantomjs-1.9.7-linux-x86_64")}
end

execute "untar phantomjs" do
	command "tar xjf phantomjs.tar.bz2"
	cwd "/home/formation"
	user "formation"
	group "formation"
	not_if { ::File.directory?("/home/formation/phantomjs-1.9.7-linux-x86_64")}
end

remote_file "/home/formation/selenium-server.jar" do
  source "http://selenium-release.storage.googleapis.com/2.42/selenium-server-standalone-2.42.2.jar"
  action :create
  owner "formation"
  group "formation"
  not_if { ::File.directory?("/home/formation/selenium-server.jar")}
end


#eclipse fix
file "/usr/local/eclipse/eclipse.ini" do
  action :delete
end

cookbook_file "eclipse.ini" do
	path "/usr/local/eclipse/eclipse.ini"
	action :create_if_missing
end

