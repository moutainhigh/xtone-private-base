<?php

class index extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	public function index() {
		$DB = $this->load('mysql',true);//数据库类
			
		$data['web'] = $DB->selectAll('cases', "*", 'status=1 order by orderSort desc, id desc', "15");
			
		$data['news1'] = $DB->selectAll('news', "*", 'nstatus=1 and ntype=1 order by nid desc', "8");
		$data['news2'] = $DB->selectAll('news', "*", 'nstatus=1 and ntype=2 order by nid desc', "8");
	
		$this->showTemplate('index', $data); 
	
	}
}

