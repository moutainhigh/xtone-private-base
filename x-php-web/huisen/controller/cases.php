<?php

class cases extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//
	public function index() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 order by orderSort desc, id desc', "40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases', $data); 
	}
	
	
	//网站开发
	public function web() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 and type=1 order by orderSort desc, id desc', "40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases-web', $data); 
	}
	
	
	//移动互联
	public function mobile() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 and type=2 order by orderSort desc, id desc',"40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases-mobile', $data); 
	}
	
	
	//电子政务
	public function government() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 and type=3 order by orderSort desc, id desc', "40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases-government', $data); 
	}
	
	
	//软件开发
	public function software() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 and type=4 order by orderSort desc, id desc', "40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases-software', $data); 
	}
	
	
	//电商运营
	public function mall() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 and type=5 order by orderSort desc, id desc', "40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases-mall', $data); 
	}
	
	
	//品牌设计
	public function brand() {
		$DB = $this->load('mysql',true);//数据库类
			
		$Arr = $DB->selectAll('cases', "*", 'status=1 and type=6 order by orderSort desc, id desc', "40");
	
		$data['data'] = $Arr; 
		$this->showTemplate('cases-brand', $data); 
	}
	
	
	//详情
	public function caseView() {
		$DB = $this->load('mysql',true);//数据库类
			
		$id = $_GET['id']?$_GET['id']:'';
		$data = $DB->selectRow('cases', "*", "id='$id'");
        
        $data['pre'] = $DB->selectRow('cases', "id", "id<'$id' order by id desc limit 1");
        $data['next'] = $DB->selectRow('cases', "id", "id>'$id' order by id asc limit 1");

		$imgUrl = $data['imgUrl'];
		$imgUrl = explode('|',$imgUrl);
		
		$data['data'] = $data;
		$data['imgUrl'] = $imgUrl; 
		$this->showTemplate('caseView', $data); 
	}
	
	
	
	
	
}

