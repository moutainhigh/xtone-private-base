<?php
include("./include/config.php");

$gameid = $_GET['gameid'];

if($gameid){
	$sql = "DELETE FROM game WHERE id='$gameid'";
	$rst = sqlsrv_query($con, $sql);
	
	if($rst){
		echo '<script type="text/javascript">alert("删除成功");location.href="listGame.php";</script>';
	}else{
		echo '<script type="text/javascript">alert("删除失败");location.href="listGame.php";</script>';
	}
}



?>
