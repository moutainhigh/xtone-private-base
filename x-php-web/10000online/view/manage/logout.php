<?php
header("Content-Type: text/html;charset=utf-8");
error_reporting (0);
session_start();

class logout extends Controller {
       
        public function __construct() {
                parent::__construct();
        }

        public function index() {
			echo 232;exit;
				$data['test'] = "";
				unset($_SESSION['username']);
				echo '<script type="text/javascript">alert("退出成功");location.href="index.php?app=manage&controller=login";</script>';
		}
		
}




?>