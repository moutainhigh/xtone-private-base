<?php
include("./include/config.php");
set_time_limit(0);

if($_POST){
	$Arr = $_POST;
	//print_r($Arr);exit;
	foreach($Arr as $key => $val){
		$Arr[$key] = iconv("UTF-8","GB2312",$val);
	}
	
	$Arr['ntype'] = $Arr['ntype'];
	$Arr['title'] = $Arr['title'];
	$Arr['source'] = $Arr['source'];
	$Arr['content'] = $Arr['content'];
	$Arr['orderSort'] = $Arr['orderSort'];
	$Arr['status'] = $Arr['status'];
	$Arr['createTime'] = date("Y-m-d H:i:s");
	
	$rst = $sqlsrv->insert("news",$Arr,true);

	
	if($rst){
		echo '<script type="text/javascript">alert("添加成功");location.href="listNews.php";</script>';
	}else{
		echo '<script type="text/javascript">alert("添加失败");location.href="listNews.php";</script>';	
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
<script charset="utf-8" src="js/manage/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="js/manage/kindeditor/lang/zh_CN.js"></script>
<script>
var editor;
KindEditor.ready(function(K) {
editor = K.create('#editor_id');
	/*上传文件设置*/
	K.create('#textarea_id', {
		themeType : 'simple',
		uploadJson : './uploads/newupload.php',
		fileManagerJson : './uploads/file_manager_json.php',
		allowFileManager : true
	});
});
</script>
</head>

<body class="ContentBody">
  <form action="" method="post" enctype="multipart/form-data" >
<div class="MainDiv" style="min-width:900px">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >新闻添加页面</th>
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
            <legend>添加新闻</legend>
            <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
            <tr>
                <td align="right">选择分类:</td>
                <td>
                	<select name="ntype" style="height:25px; width:100px;">
                    	<option value="1">新闻</option>
                    	<option value="2">测评</option>
                    </select>
                </td>
            </tr>
			
			<tr>
                <td align="right">新闻标题:</td>
                <td><input name="title" class="text" style="width:650px" type="text" size="100" />
                <span class="red"> * </span></td>
            </tr>
            
            <tr>
                <td align="right">来源:</td>
                <td><input name="source" class="text" style="width:650px" type="text" size="100" /></td>
            </tr>
			
            
           <tr height="20"><td></td><td></td></tr>
			<tr>
                <td align="right" height="120px">游戏介绍:</td>
                <td colspan="3"><textarea  id="editor_id" name="content" rows="50" cols="150"></textarea></td>
            </tr>
            
            <tr>
                <td align="right">排序:</td>
                <td><input name="orderSort" value="0" class="text" style="width:250px" type="text" size="40" /></td>
            </tr>
            
			
			 <tr>
                <td align="right">是否显示:</td>
                <td>
                	<select name="status" style="height:25px;">
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
