<?php
include("./include/config.php");

$id = $_GET['id'];

if($id){
	$sql = "DELETE FROM news WHERE id='$id'";
	$rst = sqlsrv_query($con, $sql);
	
	if($rst){
		echo '<script type="text/javascript">alert("删除成功");location.href="listNews.php";</script>';
	}else{
		echo '<script type="text/javascript">alert("删除失败");location.href="listNews.php";</script>';
	}
}



?>
