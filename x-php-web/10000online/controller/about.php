<?php

class about extends Controller {
        
        public function __construct() {
                parent::__construct();
        }

        public function index() {
			$data['test'] = '';
			
			$this->showTemplate('about', $data); 
        }
		
		//商务合作
		public function coop() {
			$data['test'] = '';
			
			$this->showTemplate('coop', $data); 
        }
		
		
}

