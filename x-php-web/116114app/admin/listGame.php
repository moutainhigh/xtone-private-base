<?php
include("./include/config.php");
include("./include/page.php");

$act = isset($_GET['act']) ? $_GET['act'] : "";

if($act == ''){

	//获取翻页列表。
	$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
	//获取总记录数。
	$totalnum = array();
	$sqlrow = "SELECT count(id) FROM game";
	$queryrow = sqlsrv_query($con, $sqlrow);
	$rows = sqlsrv_fetch_array($queryrow);
	$totalnum = $rows[0];

	//计算总页数。
	$limit = 30;//每页显示的数量。
	$totalpages = ceil($totalnum/$limit);

	$offset = ($page-1)*$limit;
	
	
	$isHome = isset($_GET['isHome']) ? "and isHome = '".$_GET['isHome']."'" : '';
	$isBanner = isset($_GET['isBanner']) ? "and isBanner = '".$_GET['isBanner']."'" : '';
	
	$selectSQL="select top $limit id,* from game left join gType on gType=gt_id 
				where id not in (select top $offset id from game ORDER BY id DESC) 
				$isHome $isBanner order by game.id desc";
				
				
	$ReRe = $sqlsrv->FetchAll($selectSQL);

	//游戏分类
	$sqlType = "select * from gType";
	$type = $sqlsrv->FetchAll($sqlType);



	if($_POST){
		//Array ( [tid] => 2 [name] => dfa [Submit4] => 查 询 )
		$tid = $_POST['tid'];
		$name = $_POST['name'];
		$namegbk = iconv("UTF-8", "GB2312", $name);
		
		if($tid==""){
			$typeSelet = "" ;
		}else{
			 $typeSelet = " and gtype='$tid'" ;
		}
		
		$ReRe = array();
		$selectSQL="select * from game left join gType on gType=gt_id where (gTitle like '%$namegbk%' or gt_title like '%$namegbk%') $typeSelet order by game.id desc";
		$ReRe = $sqlsrv->FetchAll($selectSQL);
	}
}


if($act == 'banner'){
	
	$gameID = isset($_GET['gameID']) ? $_GET['gameID'] : '';
	$isBanner = $_GET['isBanner'] == 0 ? 0 : 1;
	
	$rst = $sqlsrv->upDate("game",array("isBanner" => $isBanner),"id = '$gameID'");
	if($rst){
		echo '<script type="text/javascript">alert("修改成功");location.href="'.$_SERVER['HTTP_REFERER'].'";</script>';
	}else{
		echo '<script type="text/javascript">alert("添加失败");location.href="'.$_SERVER['HTTP_REFERER'].'";</script>';	
	}
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
    location.href="addGame.php";
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
			  <td width="21"><img src="./images/ico07.gif" width="20" height="18" /></td>
			  <td width="538">
			  <span class="newfont06">游戏类型：</span>
			  <select name="tid" style="width:100px;font-size:12px; hight:30px;">
				<option value="">全部</option>
				<?php foreach($type as $val){ ?>
				<option value="<?php echo $val['gt_id']; ?>" <?php if($tid==$val['gt_id']){ echo "selected"; } ?>><?php echo $val['gt_title']; ?></option>
				<?php } ?>
			  </select>
				&nbsp;&nbsp;&nbsp;
				<span class="newfont06">搜索内容：</span>
				<input name="name" type="text" value="<?php if($name){ echo $name; } ?>" style="width:200px;"/>&nbsp;&nbsp;
				<input name="Submit4" type="submit" class="right-button02" value="查 询" />
				</td>
			   <td width="380" align="left">
					<a href="listGame.php"><input name="Submit3" type="button" class="right-button07" value="显示所有" /></a>
					<a href="listGame.php?isBanner=1"><input name="Submit3" type="button" class="right-button07" value="显示广告位" /></a>
					<a href="listGame.php?isHome=1"><input name="Submit3" type="button" class="right-button07" value="在首页显示" /></a>
			   </td>	
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
                    	<td height="22" colspan="10" align="center" style="font-size:16px">游戏列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">id</td>
                    <td width="15%" align="center">游戏名称</td>
                    <td width="5%" align="center">游戏类别</td>
                    <td width="5%" align="center">游戏类型</td>
					<td width="5%" align="center">apk大小</td>
					<td width="10%" align="center">开发商</td>
					<td width="5%" align="center">是否为广告图</td>
					<td width="5%" align="center">首页位置</td>
					<td width="5%" align="center">状态</td>
					<td width="10%" align="center">操作</td>
                  </tr>
				  
				  <?php if($ReRe){ foreach($ReRe as $val){ ?>
                  <tr bgcolor="#FFFFFF">
				    <td align="center" height="25"><?php echo $val['id']; ?></td>
                    <td align="center"><?php echo $val['gTitle']; ?></td>
                    <td align="center"><?php if($val['gClass']==1){ echo "单机";}elseif($val['gClass']==2){ echo "网游";}else{ echo "应用";}  ?></td>
                    <td align="center"><?php echo $val['typename']; ?></td>
					<td align="center"><?php echo $val['apkSize']; ?></td>
                    <td align="center"><?php echo $val['provider']; ?></td>
					
                    <td align="center"><?php if($val['isBanner']==1){ echo "<font style='color:red;'>是</font>";}else{ echo "否";} ?></td>
					<td align="center">
						<?php 
							if($val['isHome']==1){ 
								echo "<font style='color:red;'>在首页显示</font>";
							}else{ 
								echo "首页隐藏"; 
							} 
						?>
					</td>
					
                    <td align="center"><?php if($val['status']==1){ echo "显示";}else{ echo "不显示"; } ?></td>
                    <td align="center">
						<a href="editGame.php?gameid=<?php echo $val['id']; ?>">编辑</a>|
                        <?php if($val['isBanner']==0){ ?>
						<a href="listGame.php?act=banner&isBanner=1&gameID=<?php echo $val['id']; ?>">设为广告</a>|
                        <?php }else{ ?>
                        <a href="listGame.php?act=banner&isBanner=0&gameID=<?php echo $val['id']; ?>">取消广告</a>|
                        <?php } ?>
						<a onClick="return confirm('确定要删除?');" href="deleteGame.php?gameid=<?php echo $val['id']; ?>">删除</a>
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
