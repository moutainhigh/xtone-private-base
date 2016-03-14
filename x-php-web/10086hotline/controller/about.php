<?php

class about extends Controller {
        
        public function __construct() {
                parent::__construct();
        }

        public function index() {
			$data['head'] = 'about';
			$this->showTemplate('about', $data); 
        }
		
		
		public function JoinUs() {
			$data['head'] = 'JoinUs';
			$this->showTemplate('JoinUs', $data); 
        }
		
		
		public function contact() {
			$data['head'] = 'contact';
			$this->showTemplate('contact', $data); 
        }
		
      
		
}

