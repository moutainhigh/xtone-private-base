<?php

class index extends Controller {
        
        public function __construct() {
                parent::__construct();
        }

        public function index() {
			$DB = $this->load('mysql',true);//数据库类
				
			$data['head'] = "index";
			$this->showTemplate('index', $data); 	
        }
}

