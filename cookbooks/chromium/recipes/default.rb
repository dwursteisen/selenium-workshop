package 'chromium-browser'


cookbook_file 'chromedriver_linux64.zip' do
	path "/home/formation/chromedriver_linux64.zip"
	mode 00777
	action :create
end

execute "unzip chromedriver" do
	command "unzip chromedriver_linux64.zip"
	cwd "/home/formation/"
	user "formation"
	group "formation"
	not_if { ::File.exists?("/home/formation/chromedriver")}
end