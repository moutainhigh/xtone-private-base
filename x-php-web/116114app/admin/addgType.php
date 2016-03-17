<?php
include("./include/config.php");
set_time_limit(0);

if($_POST){
	$Arr = $_POST;
	
	foreach($Arr as $key => $val){
		$Arr[$key] = iconv("UTF-8","GB2312",$val);
	}
	
	
	$Arr['gt_title'] = $Arr['gt_title'];
	$Arr['gt_status'] = $Arr['gt_status'];
	
	$rst = $sqlsrv->insert("gType",$Arr,true);

	
	if($rst){
		echo '<script type="text/javascript">alert("添加成功");location.href="listgType.php";</script>';
	}else{
		echo '<script type="text/javascript">alert("添加失败");location.href="listgType.php";</script>';	
	}
	
	
}



?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏平台管理后台</title>
<link rel="stylesheet" rev="stylesheet" href="./css/style.css" type="text/css" media="all" />
<script src="./js/jquery.js"></script>
</head>

<body class="ContentBody">
  <form action="" method="post" enctype="multipart/form-data" >
<div class="MainDiv" style="min-width:900px">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >游戏分类添加页面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
            <td align="left">
            <input type="submit" value="保存" class="button" />　
                
                <input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>
            </td>
        </tr>

        <TR>
            <TD width="100%">
            <fieldset style="height:100%;">
            <legend>添加游戏分类</legend>
            <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
			
			<tr>
                <td align="right">游戏分类名称:</td>
                <td><input name="gt_title" class="text" style="width:250px" type="text" size="40" />
                <span class="red"> * </span></td>
            </tr>
			
			
			 <tr>
                <td align="right">是否显示:</td>
                <td>
                	<select name="gt_status" style="height:25px;">
                    	<option value="1">显示</option>
                    	<option value="0">隐藏</option>
                    </select>
                </td>
            </tr>
			
			
                
            </table>
            <br />
            </fieldset>
            </TD>
        </TR>
		
		</TABLE>
	
	
	 </td>
  </tr>
  

		
		
		
		
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" value="保存" class="button" />　
			
			<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/></TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  
  </table>

</div>
</form>
</body>
</html>
