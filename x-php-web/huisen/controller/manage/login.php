<?php
session_start();
class login extends Controller {
       
        public function __construct() {
                parent::__construct();
        }

        public function index() {
				$data['test'] = ""; 
			
				
				if($_POST){
					$username = trim($_POST['username']);
					$password =trim($_POST['password']);
					$nowtime = date("Y-m-d H:i:s",time());
					
					
					if($username=='admin'&&$password=='hs@2015'){
						$_SESSION['username'] = $username;
						
						echo '<script type="text/javascript">location.href="index.php?app=manage";</script>';
					}
					else{
						echo '<script type="text/javascript">alert("用户名或密码不正确，请重新登录");location.href="index.php?app=manage&controller=login";</script>';
					}
					
				}
				
				
				$this->showTemplate('manage/login', $data); 
        }
		
		
		
		public function logout(){
		
			unset($_SESSION['username']);
			echo '<script type="text/javascript">alert("退出成功");location.href="index.php?app=manage&controller=login";</script>';
		
		}

		
		
}

