<?php

class about extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//关于我们
	public function index() {
	
		$data['test'] = ""; 
		$this->showTemplate('about', $data); 
	}
	
	
	//我们的优势
	public function superiority() {
	
		$data['test'] = ""; 
		$this->showTemplate('superiority', $data); 
	}
	
	
	//公司新闻
	public function news() {
		$DB = $this->load('mysql',true);//数据库类
	
		$data['news'] = $DB->selectAll('news', "*", 'nstatus=1 and ntype=1 order by nid desc', "8");
		$this->showTemplate('news', $data); 
	}
	
	
	//行业动态
	public function industry() {
		$DB = $this->load('mysql',true);//数据库类
	
		$data['news'] = $DB->selectAll('news', "*", 'nstatus=1 and ntype=2 order by nid desc', "8");
		$this->showTemplate('industry', $data); 
	}
	
	
	//资讯动态详情
	public function newsDetail() {
		$DB = $this->load('mysql',true);//数据库类
			
		$nid = $_GET['nid']?$_GET['nid']:'';
		$data['data'] = $DB->selectRow('news', "*", "nid='$nid'");
        
        $data['pre'] = $DB->selectRow('news', "nid", "nid<'$nid' order by nid desc limit 1");
        $data['next'] = $DB->selectRow('news', "nid", "nid>'$nid' order by nid asc limit 1");
        
		$this->showTemplate('newsDetail', $data); 
	}
	
	
	//联系方式
	public function contact() {
	
		$data['test'] = ""; 
		$this->showTemplate('contact', $data); 
	}
	
	
}

