<?php

class cases extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//案例列表
	public function listCase() {
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		//获取翻页列表。
		$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
		//获取总记录数。
		$totalnum = array();
		$query = mysql_query("SELECT count(*) FROM cases");
		$num = mysql_fetch_row($query);
		$totalnum = $num[0];
		//计算总页数。
		$limit = 20;//每页显示的数量。
		$totalpages = ceil($totalnum/$limit);
		
		$casesType = $DB->selectAll('casesType', "*", '', "");
		$data['casesType'] = $casesType; 
		
		$data['data'] = $DB->selectAll('cases', "*", " id>0 order by id desc", ($page-1)*$limit.','.$limit);
		$data['page'] = $Func->page($totalnum,$limit);
		
		if($_POST){
			$tid = $_POST['tid'];
			$title = $_POST['title'];
			
			if($tid==""){
				$typeSelet = "" ;
			}else{
				 $typeSelet = " and type='$tid'" ;
			}
			$ReRe = array();
			$data['data'] = $DB->selectAll('cases', "*", "title like '%$title%' $typeSelet order by id desc", "");
			$data['tid'] = $tid;
			$data['title'] = $title;
		}

	
		$this->showTemplate('manage/listCases', $data); 
	}
	
	
	public function addCases(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		if($_POST){
			$type = $_POST['type'];
			$tName = $_POST['tName'];
			$title = $_POST['title'];
			$siteUrl = $_POST['siteUrl'];
			$introduce = addslashes($_POST['introduce']);
			$sign = $_POST['sign'];
			$status = $_POST['status'];
			$orderSort = $_POST['orderSort'];
			$createTime = date('Y-m-d H:i:s');
			
			//icon
			if($_FILES['icon']['tmp_name']){
				$icon = $Func->upload($_FILES['icon'],'./uploads/icon/');
			}
			
			
			if($_FILES){
			
				foreach($_FILES as $key => $val){
					
					if(substr($key,0,7) == 'showImg' && $_FILES[$key]['tmp_name']){
						$picture[] = $Func->upload($_FILES[$key],'./uploads/showImg/');
					}
				}
				
				if($picture)
					$showImg = implode("|",$picture);
				
			
			}
			
			
			$DB->insert('cases', 'type,tName,title,icon,imgUrl,introduce,sign,siteUrl,status,createTime', "'$type','$tName','$title','$icon','$showImg','$introduce','$sign','$siteUrl','$status','$createTime'", 'index.php?app=manage&controller=cases&action=listCase');exit;
			
			
		}
	
		$casesType = $DB->selectAll('casesType', "*", '', $limit = "");
		$data['casesType'] = $casesType; 
		$this->showTemplate('manage/addCases', $data); 
	}
	
	
	public function editCases(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		$id = $_GET['id']?$_GET['id']:'';
			
			
		$Row = $DB->selectRow('cases', "*", "id='$id'");
		$data['Row'] = $Row; 
		if($data['Row']['imgUrl']){
			$data['picArr'] = explode("|",$data['Row']['imgUrl']);
		}
		
		$casesType = $DB->selectAll('casesType', "*", '', $limit = "");
		$data['casesType'] = $casesType; 
		
		
		if($_POST){
			$id = $_POST['id'];
			$type = $_POST['type'];
			$tName = $_POST['tName'];
			$title = $_POST['title'];
			$sign = $_POST['sign'];
			$introduce = addslashes($_POST['introduce']);
			$siteUrl = $_POST['siteUrl'];
			$orderSort = $_POST['orderSort'];
			$status = $_POST['status'];
			$createTime = date('Y-m-d H:i:s');
			$condition = "";
			
			
			if($_FILES['icon']['tmp_name']){
				$icon = $Func->upload($_FILES['icon'],'./uploads/icon/');
				
				$condition = ",icon='$icon'";
			}
			
			
			if($_FILES){
				
				foreach($_FILES as $key => $val){
					
					if(substr($key,0,7) == 'showImg' && $_FILES[$key]['tmp_name']){
						$picture[] = $Func->upload($_FILES[$key],'./uploads/showImg/');
					}
				}
			
				if($picture){
				
					if($data['picArr']){
						$picture = array_merge($picture, $data['picArr']);
					}
					
					$showImg = implode("|",$picture);
					$condition = $condition.",imgUrl='$showImg'";
				}
				
				
			
			}
			
			$DB->update('cases', "title='$title',type='$type',tName='$tName',sign='$sign',introduce='$introduce',siteUrl='$siteUrl',orderSort='$orderSort',status='$status',createTime='$createTime' $condition", 'id='.$id,'index.php?app=manage&controller=cases&action=listCase');exit;
			
			
		}
		
		
		
		//删除图片
		if($_GET['delPic']=='delPic'){
			
			$delName = isset($_GET['picName']) ? $_GET['picName'] : '';
			$id = isset($_GET['id']) ? $_GET['id'] : '';
	
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
			
				$DB->update('cases', "imgUrl='$picArr'", 'id='.$id,'index.php?app=manage&controller=cases&action=listCase');
				
			}
		}
		
		
		$this->showTemplate('manage/editCases', $data); 
	}
	
	
	public function deleteCases(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		
		$id = $_GET['id']?$_GET['id']:'';
		
		if($id){
			
			$DB->delete('cases', "id='$id'", $url = 'index.php?app=manage&controller=cases&action=listCase');
			
		}
	}
	
	
	
}

