<?php

class product extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//域名空间邮箱
	public function index() {
	
		$data['test'] = ""; 
		$this->showTemplate('product', $data); 
	}
	
	
	//网站设计服务
	public function design() {
	
		$data['test'] = ""; 
		$this->showTemplate('product-design', $data); 
	}
	
	
	//网站SEO优化
	public function seo() {
	
		$data['test'] = ""; 
		$this->showTemplate('product-seo', $data); 
	}
	
	
	//淘宝阿里装修
	public function beautify() {
	
		$data['test'] = ""; 
		$this->showTemplate('product-beautify', $data); 
	}
	
	
	//手机网站
	public function mobile() {
	
		$data['test'] = ""; 
		$this->showTemplate('product-mobile', $data); 
	}
	
	
	//应用软件开发
	public function store() {
	
		$data['test'] = ""; 
		$this->showTemplate('product-store', $data); 
	}
	
	
	//增值设计服务
	public function services() {
	
		$data['test'] = ""; 
		$this->showTemplate('product-services', $data); 
	}
	
	
}

