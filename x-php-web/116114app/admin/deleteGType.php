<?php
include("./include/config.php");

$gTid = $_GET['gTid'];

if($gTid){
	$sql = "DELETE FROM gType WHERE gt_id='$gTid'";
	$rst = sqlsrv_query($con, $sql);
	
	if($rst){
		echo '<script type="text/javascript">alert("删除成功");location.href="listgType.php";</script>';
	}else{
		echo '<script type="text/javascript">alert("删除失败");location.href="listgType.php";</script>';
	}
}



?>
