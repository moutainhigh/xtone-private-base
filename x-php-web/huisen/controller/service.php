<?php
session_start();
class service extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//服务理念
	public function index() {
	
		$data['test'] = ""; 
		$this->showTemplate('service', $data); 
	}
	
	
	//FAQ
	public function faq() {
	
		$data['test'] = ""; 
		$this->showTemplate('service-faq', $data); 
	}
	
	
	//FAQ
	public function faq2() {
	
		$data['test'] = ""; 
		$this->showTemplate('service-faq2', $data); 
	}
	
	
	//FAQ
	public function faq3() {
	
		$data['test'] = ""; 
		$this->showTemplate('service-faq3', $data); 
	}
	
	
	//FAQ
	public function faq4() {
	
		$data['test'] = ""; 
		$this->showTemplate('service-faq4', $data); 
	}
	
	
	//提交反馈
	public function feedback() {
		if($_POST){
			if($_POST['imgcode']!=$_SESSION['admin_verify']){
				echo '<script type="text/javascript">alert("验证码错误");location.href="index.php?controller=service&action=feedback"</script>';exit;
			}
			//print_r($_POST);exit;
			//Array ( [name] => fdsf [company] => fadsf [tel] => fadsfa [content] => fdsfad [code] => dfas [button] => )
			
			
		}
		$data['test'] = ""; 
		$this->showTemplate('service-feedback', $data); 
	}
	
	public function verify() {
	
		$data['test'] = ""; 
		$this->showTemplate('verify', $data); 
	}
	
}

