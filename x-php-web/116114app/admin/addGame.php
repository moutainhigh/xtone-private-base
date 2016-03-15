<?php
include("./include/config.php");
set_time_limit(0);

$SQL = "select * from gType";
$type = $sqlsrv->FetchAll($SQL);

if($_POST){
	include("./include/upload_class.php");
	$upload = new UploadFile;
	$Arr = $_POST;
	
	foreach($Arr as $key => $val){
		$Arr[$key] = iconv("UTF-8","GB2312",$val);
	}
	
	//游戏图标
	if($_FILES['icon']['tmp_name']){
		$Arr['icon'] = $upload->upload($_FILES['icon'],'resources/uploadlogo/');
	}
	
	//广告位图片地址
	if($_FILES['bigBannerUrl']['tmp_name']){
		$Arr['bigBannerUrl'] = $upload->upload($_FILES['bigBannerUrl'],'resources/uploadlogo/');
	}
	
	//广告位缩略图
	if($_FILES['smallBannerUrl']['tmp_name']){
		$Arr['smallBannerUrl'] = $upload->upload($_FILES['smallBannerUrl'],'resources/uploadlogo/');
	}
	
	
	if($Arr['apkUploadType'] == 1){
		
		if($_FILES['apkLink']['tmp_name']){
			$Arr['apkLink'] = $upload->upload($_FILES['apkLink'],'resources/apk/');
			$Arr['apkLink'] = 'http://61.142.254.126:8085/'.$Arr['apkLink'];
		}
		
	}else{
		$Arr['apkLink'] = $Arr['apkLink'];
	}
	unset($Arr['apkUploadType']);
	/*unset($Arr['apkLink']);
	unset($Arr['apkSize']);*/
	
	
	if($_FILES){
		
		foreach($_FILES as $key => $val){
			
			if(substr($key,0,10) == 'ga_picture' && $_FILES[$key]['tmp_name']){
				$ga_picture[] = $upload->upload($_FILES[$key],'resources/uploadimg/');
			}
		}
		
		if($ga_picture)
			$Arr['screenshot'] = implode("|",$ga_picture);
		
	
	}
	
	
	
	$Arr['provider'] = $Arr['provider'];
	$Arr['isHome'] = $Arr['isHome'];
	$Arr['isBanner'] = $Arr['isBanner'];
	$Arr['apkSize'] = $Arr['apkSize'];
	$Arr['orderSort'] = $Arr['orderSort'];
	$Arr['createTime'] = date("Y-m-d H:i:s");
	
	$rst = $sqlsrv->insert("game",$Arr,true);

	
	if($rst){
		echo '<script type="text/javascript">alert("添加成功");location.href="listGame.php";</script>';
	}else{
		echo '<script type="text/javascript">alert("添加失败");location.href="listGame.php";</script>';	
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
      <th class="tablestyle_title" >游戏添加页面</th>
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
            <legend>添加游戏</legend>
            <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
			
			<tr>
                <td align="right">游戏名:</td>
                <td><input name="gTitle" class="text" style="width:250px" type="text" size="40" />
                <span class="red"> * 如果是升级版本,游戏名必须保持一致</span></td>
            </tr>
			
            <tr>
                <td align="right">游戏类别:</td>
				<td>
                	<select name="gClass" style="height:25px;">
                    	<option value="1">单机</option>
                    	<option value="2">网游</option>
                    	<option value="3">应用</option>
                    </select>
                </td>
            </tr>
			
			<tr>
                <td align="right">游戏类型:</td>
				<td id="gaType">
                	<select name="gType" style="height:25px;" id ="ga_type">
						<?php foreach($type as $val){ ?>
                    	<option value="<?php echo $val['gt_id']; ?>"><?php echo $val['gt_title']; ?></option>
						<?php } ?>
                    </select>
					<input name="typename" type="hidden" />
                </td>
				
				<script>
					$("#ga_type").change(function(){
						$("[name='typename']").val($("#ga_type option:selected").text());
					});
					
					$(function(){
						$("[name='typename']").val($("#ga_type option:selected").text());
					});
				</script>
				
            </tr>
			
			
			 <tr>
                <td align="right">游戏图标:</td>
                <td><input name="icon" type="file" style="width:140px;" /><span class="red"> *width:150px; height:150px;</span></td>
            </tr>
            
            <tr>
                <td align="right">下载次数:</td>
                <td><input name="downloadCount" class="text" value="0" style="width:250px" type="text" size="40" /></td>
            </tr>
			
			<tr>
                <td align="right">显示在页面的大小:</td>
                <td><input name="apkSize" type="text"  size="40" /><span class="red"> * 如：13.16M</span></td>
            </tr>
            
            <tr>
                <td align="right">开发商:</td>
                <td><input name="provider" class="text" style="width:250px" type="text" size="40" /></td>
            </tr>
			
            <tr>
                <td align="right">apk方式:</td>
                <td>
				<select name="apkUploadType" id="apkUploadType">
					<option value="1">上传APK</option>
					<option value="2">输入APK路径</option>
				</select></td>
            </tr>
			
			<tr>
                <td align="right">apk</td>
                <td id ="apkLink"><input name="apkLink" type="file" /></td>
            </tr>
			<script>
				$("#apkUploadType").change(function(){
					
					$("#apkLink").html("");
					
					
					if($(this).val() == 1){
						$("#apkLink").append('<input name="apkLink" type="file" />');
					}else{
						$("#apkLink").append('<input name="apkLink" type="text" size="80" /><span class="red"> *路径： 必须带完整http://开头</span><br/>');
					}
				});
			</script>
           
			<tr>
                <td align="right" height="120px">游戏介绍:</td>
                <td colspan="3"><textarea id="textarea" name="introduce" rows="10" cols="120"></textarea></td>
            </tr>
            
            <tr>
                <td align="right">排序:</td>
                <td><input name="orderSort" value="0" class="text" style="width:250px" type="text" size="40" /></td>
            </tr>
            
            <tr height="20"><td></td><td></td></tr>
            <tr>
                <td align="right" valign="top">游戏截图:</td>
                <td id="PicTD">
					<span class="red"> <a href="javascript:void(0)" onclick="addPic()">增加图片</a> *width:585px; height:255px;</span><br/><br/>
					
				</td>
				<script>
					var num = 1;
					
					function addPic(){
						$("#PicTD").append('<input name="ga_picture_'+num+'" type="file" /><br/>');
						num ++;
					}
				</script>
            </tr>
			
            <tr height="20"><td></td><td></td></tr>
			<tr>
                <td align="right">广告位banner显示:</td>
                <td>
                	<select name="isBanner" style="height:25px;">
						<option value="0">不显示</option>
                    	<option value="1">显示</option>
                    </select>
                </td>
            </tr>
			<tr>
                <td align="right">广告banner大图:</td>
                <td><input name="bigBannerUrl" type="file" style="width:140px;" /><span class="red"> (需要展示则上传图片) 970*405</span></td>
            </tr>
			<tr>
                <td align="right">广告banner缩略图:</td>
                <td><input name="smallBannerUrl" type="file" style="width:140px;" /><span class="red"> (需要展示则上传图片) 167*66</span></td>
            </tr>
			
            <tr height="20"><td></td><td></td></tr>
            
			<tr>
                <td align="right">首页位置显示:</td>
                <td>
                	<select name="isHome" style="height:25px;">
                    	<option value="0">不在首页显示</option>
						<option value="1">在热门网游 或精品单机显示</option>
                    </select>
                </td>
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
