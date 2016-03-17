<?php
include("./include/config.php");

//游戏分类
$sqlType = "select * from gType";
$type = $sqlsrv->FetchAll($sqlType);



?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏平台管理后台</title>
<link href="./css/css.css" rel="stylesheet" type="text/css" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT language=JavaScript>

function link(){
    location.href="addgType.php";
}

</SCRIPT>

<body>
<form name="fom" id="fom" method="post" action="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td height="30">      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="./images/nav04.gif">
            
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		    <tr>
			  <td width="21"></td>
			  <td width="538"></td>
			   <td width="380" align="left"></td>	
		    </tr>
          </table></td>
        </tr>
    </table></td></tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 
			 <tr>
               <td height="20">
	              <input type="button" class="right-button08" value="添加任务" onclick="link();"/>
				</td>
          	 </tr>
			 
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="4" align="center" style="font-size:16px">游戏分类列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">id</td>
                    <td width="20%" align="center">游戏分类名称</td>
					<td width="5%" align="center">状态</td>
					<td width="10%" align="center">操作</td>
                  </tr>
				  
				  <?php if($type){ foreach($type as $val){ ?>
                  <tr bgcolor="#FFFFFF">
				    <td align="center" height="25"><?php echo $val['gt_id']; ?></td>
                    <td align="center"><?php echo $val['gt_title']; ?></td>
					
                    <td align="center"><?php if($val['gt_status']==1){ echo "显示";}else{ echo "不显示"; } ?></td>
                    <td align="center">
						<a href="editgType.php?gtID=<?php echo $val['gt_id']; ?>">编辑</a>|
						<a onClick="return confirm('确定要删除?');" href="deleteGType.php?gTid=<?php echo $val['gt_id']; ?>">删除</a>
					</td>
                  </tr>
				 <?php } } ?>
            </table></td>
        </tr>
      </table>
     </td>
  </tr>
</table>
<div style="height:30px;"></div>
</form>
</body>
</html>
