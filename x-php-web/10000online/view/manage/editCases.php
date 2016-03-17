<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州捷酷软件--管理后台</title>
<link rel="stylesheet" rev="stylesheet" href="./css/manage/style.css" type="text/css" media="all" />
<script src="./js/manage/jquery.js"></script>
</head>

<body class="ContentBody">
<form action="" method="post" enctype="multipart/form-data" >
<input type="hidden" name="id" value="<?php echo $Row['id']; ?>" />
<div class="MainDiv" style="min-width:900px">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >案例编辑页面</th>
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
            <legend>编辑案例</legend>
            <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
			
			<tr>
                <td align="right">案例标题:</td>
                <td><input name="title" value="<?php echo $Row['title']; ?>" class="text" style="width:250px" type="text" size="40" />
                <span class="red"> * </span></td>
            </tr>
			
			
			<tr>
                <td align="right">所属类型:</td>
				<td id="gaType">
                	<select name="type" style="height:25px;" id ="type">
                    	<?php if($casesType){ foreach($casesType as $val){ ?>
                    	<option value="<?php echo $val['tid']; ?>" <?php if($val['tid']==$Row['type']){ echo  "selected='selected'";} ?>><?php echo $val['tName']; ?></option>
                        <?php }} ?>
                    </select>
					<input name="tName" type="hidden" />
                </td>
            </tr>
			<script>
                $("#type").change(function(){
                    $("[name='tName']").val($("#type option:selected").text());
                });
                
                $(function(){
                    $("[name='tName']").val($("#type option:selected").text());
                });
            </script>
			
			<tr>
                <td align="right">标识或说明:</td>
                <td><input name="sign" value="<?php echo $Row['sign']; ?>" class="text" style="width:250px" type="text" /></td>
            </tr>
            
            <tr>
                <td align="right">网址:</td>
                <td><input name="siteUrl" value="<?php echo $Row['siteUrl']; ?>" class="text" style="width:250px" type="text" /><span class="red"> * 以 http:// 开头</span></td>
            </tr>
			
			
			 <tr>
                <td align="right">图标:</td>
                <td><input name="icon" type="file" style="width:140px;" /><span class="red"> *width:240px; height:330px;</span>
              <br/><img src="<?php if($Row['icon']){ echo $Row['icon'];} ?>" width="240" /></td>
            </tr>
            
         	<tr height="15">
                <td align="right"></td>
                <td></td>
            </tr>
            <tr>
                <td align="right" valign="top">上传多组产品展示图:</td>
                <td id="PicTD">
					<span class="red"> <a href="javascript:void(0)" onclick="addPic()">增加图片</a> *width:890px;</span><br/><br/>
					
				</td>
            <script>
				var num = 1;
				
				function addPic(){
					$("#PicTD").append('<input name="showImg_'+num+'" type="file" /><br/>');
					num ++;
				}
			</script>
            </tr>
            
            <tr>
			<?php if($picArr!=''){  ?>
            <td></td>
            <td>
            	<span style="color:red;">点击图片删除;</span>
					<br/>
					<br/>
					<?php
						foreach($picArr as $key => $val){
					?>
						<a href="index.php?app=manage&controller=cases&action=editCases&delPic=delPic&id=<?php echo $Row['id'];?>&picName=<?php echo urldecode($val); ?>" onClick="return confirm('确定要删除此张图?');"><img src="<?php echo $val; ?>" width="240" /></a>
					<?php
					} 
					?>
            </td>
            <?php } ?>
           </tr>
           
           
			<tr>
                <td align="right" height="120px">案例介绍:</td>
                <td colspan="3"><textarea id="textarea" name="introduce" rows="10" cols="120"><?php echo $Row['introduce']; ?></textarea></td>
            </tr>
            
            <tr>
                <td align="right">排序:</td>
                <td><input name="orderSort" value="<?php echo $Row['orderSort']; ?>" class="text" style="width:250px" type="text" size="40" /></td>
            </tr>
			
		
			 <tr>
                <td align="right">是否显示:</td>
                <td>
                	<select name="status" style="height:25px;">
                    	<option value="1" <?php if($Row['status']==1){ echo "selected";} ?>>显示</option>
                    	<option value="0" <?php if($Row['status']==0){ echo "selected";} ?>>隐藏</option>
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
