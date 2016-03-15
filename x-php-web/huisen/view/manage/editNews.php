<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>荟森数码--管理后台</title>
<link rel="stylesheet" rev="stylesheet" href="./css/manage/style.css" type="text/css" media="all" />
<script src="./js/manage/jquery.js"></script>
<!--HTML编辑器-->
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
<input type="hidden" name="nid" value="<?php echo $Row['nid']; ?>" />
<div class="MainDiv" style="min-width:900px">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >新闻资讯编辑页面</th>
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
            <legend>编辑新闻资讯</legend>
            <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
			
			<tr>
                <td align="right">新闻资讯标题:</td>
                <td><input name="ntitle" value="<?php echo $Row['ntitle']; ?>" class="text" style="width:250px" type="text" size="40" />
                <span class="red"> * </span></td>
            </tr>
			
			
			<tr>
                <td align="right">所属类型:</td>
				<td id="gaType">
                	<select name="ntype" style="height:25px;" id="ntype">
                    	<?php if($newsType){ foreach($newsType as $val){ ?>
                    	<option value="<?php echo $val['ntid']; ?>" <?php if($val['ntid']==$Row['ntype']){ echo  "selected='selected'";} ?>><?php echo $val['ntName']; ?></option>
                        <?php }} ?>
                    </select>
					<input name="ntName" type="hidden" />
                </td>
            </tr>
			<script>
                $("#ntype").change(function(){
                    $("[name='ntName']").val($("#ntype option:selected").text());
                });
                
                $(function(){
                    $("[name='ntName']").val($("#ntype option:selected").text());
                });
            </script>
			
            <tr>
                <td align="right" height="120px">简介（部分文字）:</td>
                <td colspan="3"><textarea  name="ninfor" rows="6" cols="120"><?php echo $Row['ninfor']; ?></textarea></td>
            </tr>
			
			
           
			<tr>
                <td align="right" height="120px">新闻资讯内容:</td>
                <td colspan="3"><textarea id="editor_id" name="ncontent" rows="50" cols="150"><?php echo $Row['ncontent']; ?></textarea></td>
            </tr>
            
			 <tr>
                <td align="right">是否显示:</td>
                <td>
                	<select name="nstatus" style="height:25px;">
                    	<option value="1" <?php if($Row['nstatus']==1){ echo "selected";} ?>>显示</option>
                    	<option value="0" <?php if($Row['nstatus']==0){ echo "selected";} ?>>隐藏</option>
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
