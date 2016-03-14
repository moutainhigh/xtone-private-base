<?php
session_start();
class index extends Controller {
        
        public function __construct() {
                parent::__construct();
        }

        public function index() {
				$data['test'] = ""; 
				if(!$_SESSION['username']){
					echo '<script type="text/javascript">location.href="index.php?app=manage&controller=login";</script>';exit;
				}
				$data['username'] = $_SESSION['username'];
				//print_r($data['username']);exit;
				
				$this->showTemplate('manage/index', $data); 
			
        }

        public function left() {
				$data['test'] = ""; 
				$this->showTemplate('manage/left', $data); 
			
        }

        public function top() {
				$data['test'] = ""; 
				$this->showTemplate('manage/top', $data); 
			
        }

        public function mainfra() {
				$data['test'] = ""; 
				$this->showTemplate('manage/mainfra', $data); 
			
        }
		
		
}

