<?php
include("./include/config.php");
include("./include/page.php");

//获取翻页列表。
$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
//获取总记录数。
$totalnum = array();
$sqlrow = "SELECT count(id) FROM news";
$queryrow = sqlsrv_query($con, $sqlrow);
$rows = sqlsrv_fetch_array($queryrow);
$totalnum = $rows[0];

//计算总页数。
$limit = 30;//每页显示的数量。
$totalpages = ceil($totalnum/$limit);

$offset = ($page-1)*$limit;

$sqlNews = "select top $limit n.id,* from news as n where n.id not in (select top $offset id from news ORDER BY id DESC)  order by n.id desc";
$news = $sqlsrv->FetchAll($sqlNews);

if($_POST){
	
	$tid = $_POST['tid'];
	$name = $_POST['name'];
	$namegbk = iconv("UTF-8", "GB2312", $name);
	
	if($tid==""){
		$typeSelet = "" ;
	}else{
		 $typeSelet = " and ntype='$tid'" ;
	}
	
	$ReRe = array();
	$selectSQL="select * from news where title like '%$namegbk%' $typeSelet order by id desc";
	$news = $sqlsrv->FetchAll($selectSQL);
}

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
    location.href="addNews.php";
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
			  <td width="538">
              <span class="newfont06">分类：</span>
			  <select name="tid" style="width:100px;font-size:12px; hight:30px;">
				<option value="">全部</option>
				<option value="1" <?php if(isset($tid)&&$tid==1){ echo "selected"; } ?>>新闻</option>
				<option value="2" <?php if(isset($tid)&&$tid==2){ echo "selected"; } ?>>测评</option>
			  </select>
				&nbsp;&nbsp;&nbsp;
				<span class="newfont06">搜索内容：</span>
				<input name="name" type="text" value="<?php if($name){ echo $name; } ?>" style="width:200px;"/>&nbsp;&nbsp;
				<input name="Submit4" type="submit" class="right-button02" value="查 询" />
              </td>
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
                    	<td height="22" colspan="7" align="center" style="font-size:16px">新闻列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">id</td>
                    <td width="5%" align="center">分类</td>
                    <td width="30%" align="center">新闻标题</td>
					<td width="10%" align="center">来源</td>
					<td width="5%" align="center">排序</td>
					<td width="5%" align="center">状态</td>
					<td width="5%" align="center">操作</td>
                  </tr>
				  
				  <?php if($news){ foreach($news as $val){ ?>
                  <tr bgcolor="#FFFFFF">
				    <td align="center" height="25"><?php echo $val['id']; ?></td>
                    <td align="center"><?php if($val['ntype']==1){echo "新闻";}elseif($val['ntype']==2){echo "测评";} ?></td>
                    <td align="left"><?php echo $val['title']; ?></td>
                    <td align="center"><?php echo $val['source']; ?></td>
                    <td align="center"><?php echo $val['orderSort']; ?></td>
                    <td align="center"><?php if($val['status']==1){ echo "显示";}else{ echo "不显示"; } ?></td>
                    <td align="center">
						<a href="editNews.php?nid=<?php echo $val['id']; ?>">编辑</a>|
						<a onClick="return confirm('确定要删除?');" href="deleteNews.php?id=<?php echo $val['id']; ?>">删除</a>
					</td>
                  </tr>
				 <?php } } ?>
            </table></td>
        </tr>
      </table>
     </td>
  </tr>
</table>
<p style="width:100%; text-align:center; font-size:12px;"><?php echo page($totalnum,$limit); ?></p>
<div style="height:30px;"></div>
</form>
</body>
</html>
