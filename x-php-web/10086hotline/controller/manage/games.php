<?php

class games extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//游戏列表
	public function listGames() {
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		//获取翻页列表。
		$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
		//获取总记录数。
		$totalnum = array();
		$query = mysql_query("SELECT count(*) FROM games");
		$num = mysql_fetch_row($query);
		$totalnum = $num[0];
		//计算总页数。
		$limit = 20;//每页显示的数量。
		$totalpages = ceil($totalnum/$limit);
		
		$gType = $DB->selectAll('gType', "*", '', "");
		$data['gType'] = $gType; 
		
		$data['data'] = $DB->selectAll('games', "*", " id>0 order by id desc", ($page-1)*$limit.','.$limit);
		$data['page'] = $Func->page($totalnum,$limit);
		
		if($_POST){
			$tid = $_POST['tid'];
			$gTitle = $_POST['gTitle'];
			
			if($tid==""){
				$typeSelet = "" ;
			}else{
				 $typeSelet = " and gType='$tid'" ;
			}
			$ReRe = array();
			$data['data'] = $DB->selectAll('games', "*", "gTitle like '%$gTitle%' $typeSelet order by id desc", "");
			$data['tid'] = $tid;
			$data['gTitle'] = $gTitle;
		}

	
		$this->showTemplate('manage/listGames', $data); 
	}
	
	
	public function addGame(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		if($_POST){
			$Arr = $_POST;
			
			$gTitle = $_POST['gTitle'];
			$gClass = $_POST['gClass'];
			$gType = $_POST['gType'];
			$typename = $_POST['typename'];
			$downloadCount = $_POST['downloadCount'];
			$apkSize = $_POST['apkSize'];
			$provider = $_POST['provider'];
			$apkUploadType = $_POST['apkUploadType'];
			$introduce = addslashes($_POST['introduce']);
			$orderSort = $_POST['orderSort'];
			$isBanner = $_POST['isBanner'];
			$isHome = $_POST['isHome'];
			$status = $_POST['status'];
			$createTime = date('Y-m-d H:i:s');
			
			//游戏图标
			if($_FILES['icon']['tmp_name']){
				$icon = $Func->upload($_FILES['icon'],'resources/uploadlogo/');
			}
			
			//广告位图片地址
			if($_FILES['bigBannerUrl']['tmp_name']){
				$bigBannerUrl = $Func->upload($_FILES['bigBannerUrl'],'resources/uploadlogo/');
			}
			
			if($Arr['apkUploadType'] == 1){
				
				if($_FILES['apkLink']['tmp_name']){
					$Arr['apkLink'] = $Func->upload($_FILES['apkLink'],'resources/apk/');
					$apkLink = $Arr['apkLink'];
				}
			}else{
				$apkLink = $Arr['apkLink'];
			}
			unset($Arr['apkUploadType']);
			
			if($_FILES){
		
				foreach($_FILES as $key => $val){
					
					if(substr($key,0,10) == 'ga_picture' && $_FILES[$key]['tmp_name']){
						$ga_picture[] = $Func->upload($_FILES[$key],'resources/uploadimg/');
					}
				}
				
				if($ga_picture)
					$screenshot = implode("|",$ga_picture);
			}
			
			$DB->insert('games', 'gTitle,gClass,gType,typename,downloadCount,apkSize,provider,introduce,orderSort,isBanner,isHome,status,icon,bigBannerUrl,apkLink,screenshot,createTime', "'$gTitle','$gClass','$gType','$typename','$downloadCount','$apkSize','$provider','$introduce','$orderSort','$isBanner','$isHome','$status','$icon','$bigBannerUrl','$apkLink','$screenshot','$createTime'", 'index.php?app=manage&controller=games&action=listGames');exit;
			
		}
	
		$gType = $DB->selectAll('gType', "*", '', $limit = "");
		$data['gType'] = $gType; 
		$this->showTemplate('manage/addGame', $data); 
	}
	
	
	public function editGame(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		$gameid = $_GET['gameid']?$_GET['gameid']:'';
			
			
		$ArrRow = $DB->selectRow('games', "*", "id='$gameid'");
		$data['ArrRow'] = $ArrRow; 
		$data['gameid'] = $gameid; 
		
		if($data['ArrRow']){
			$data['picArr'] = explode("|",$data['ArrRow']['screenshot']);
		}
		
		$gType = $DB->selectAll('gType', "*", '', $limit = "");
		$data['gType'] = $gType; 
		
		
		if($_POST){
			$Arr = $_POST;
			//print_r($Arr);exit;
			$id = $_POST['id'];
			$gTitle = $_POST['gTitle'];
			$gClass = $_POST['gClass'];
			$gType = $_POST['gType'];
			$typename = $_POST['typename'];
			$downloadCount = $_POST['downloadCount'];
			$apkSize = $_POST['apkSize'];
			$provider = $_POST['provider'];
			$apkUploadType = $_POST['apkUploadType'];
			$introduce = addslashes($_POST['introduce']);
			$orderSort = $_POST['orderSort'];
			$isBanner = $_POST['isBanner'];
			$isHome = $_POST['isHome'];
			$status = $_POST['status'];
			$createTime = date('Y-m-d H:i:s');
			$condition = "";
			
			
			if($_FILES['icon']['tmp_name']){
				$icon = $Func->upload($_FILES['icon'],'./uploads/icon/');
				
				$condition = ",icon='$icon'";
			}
			
			if($_FILES['bigBannerUrl']['tmp_name']){
				$bigBannerUrl = $Func->upload($_FILES['bigBannerUrl'],'./uploads/uploadimg/');
				
				$condition = ",bigBannerUrl='$bigBannerUrl'";
			}
			
			if($Arr['apkUploadType'] == 1){
				
				if($_FILES['apkLink']['tmp_name']){
					$Arr['apkLink'] = $Func->upload($_FILES['apkLink'],'resources/apk/');
					$apkLink = $Arr['apkLink'];
				}
			}else{
				$apkLink = $Arr['apkLink'];
			}
			unset($Arr['apkUploadType']);
			
			if($_FILES){
		
				foreach($_FILES as $key => $val){
					
					if(substr($key,0,10) == 'ga_picture' && $_FILES[$key]['tmp_name']){
						$ga_picture[] = $Func->upload($_FILES[$key],'resources/uploadimg/');
					}
				}
				
				if($ga_picture){
					if($data['picArr']){
						$ga_picture = array_merge($ga_picture, $data['picArr']);
					}
					$screenshot = implode("|",$ga_picture);
					$condition = $condition.",screenshot='$screenshot'";
				}
			}
			
			//print_r($condition);exit;
			$DB->update('games', "gTitle='$gTitle',gClass='$gClass',gType='$gType',typename='$typename',downloadCount='$downloadCount',apkSize='$apkSize',provider='$provider',introduce='$introduce',orderSort='$orderSort',isBanner='$isBanner',isHome='$isHome',status='$status',apkLink='$apkLink',createTime='$createTime' $condition", 'id='.$id,'index.php?app=manage&controller=games&action=listGames');exit;
			
			
		}
		
		
		
		//删除图片
		if($_GET['delPic']=='delPic'){
			$delName = isset($_GET['picName']) ? $_GET['picName'] : '';
			$id = isset($_GET['gameid']) ? $_GET['gameid'] : '';
	
			if($data['picArr']){
				
				if(file_exists($delName))
				{
					@unlink($delName);
				}
					
				foreach($data['picArr'] as $key =>$val){
					if($val == $delName)
						unset($data['picArr'][$key]);
				}
				
				
				$picArr = implode("|",$data['picArr']);
				
				$DB->update('games', "screenshot='$picArr'", 'id='.$id,'index.php?app=manage&controller=games&action=listGames');
				
			}
		}
		
		
		$this->showTemplate('manage/editGame', $data); 
	}
	
	
	public function deleteGame(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		
		$gameid = $_GET['gameid']?$_GET['gameid']:'';
		
		if($gameid){
			
			$DB->delete('games', "id='$gameid'", $url = 'index.php?app=manage&controller=games&action=listGames');
			
		}
	}
	
	
	
}

