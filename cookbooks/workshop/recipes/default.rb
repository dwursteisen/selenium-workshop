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
