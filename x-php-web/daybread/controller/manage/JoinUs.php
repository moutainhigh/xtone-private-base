<?php

class JoinUs extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//案例列表
	public function listJoinUs() {
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		//获取翻页列表。
		$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
		//获取总记录数。
		$totalnum = array();
		$query = mysql_query("SELECT count(*) FROM JoinUs");
		$num = mysql_fetch_row($query);
		$totalnum = $num[0];
		//计算总页数。
		$limit = 20;//每页显示的数量。
		$totalpages = ceil($totalnum/$limit);
		
		
		$data['data'] = $DB->selectAll('JoinUs', "*", "", ($page-1)*$limit.','.$limit);
		$data['page'] = $Func->page($totalnum,$limit);
		
		if($_POST){
			$title = $_POST['title'];
			
			$ReRe = array();
			$data['data'] = $DB->selectAll('JoinUs', "*", "title like '%$title%' order by id desc", "");
			$data['title'] = $title;
		}

	
		$this->showTemplate('manage/listJoinUs', $data); 
	}
	
	
	public function addJoinUs(){
		$DB = $this->load('mysql',true);//数据库类
		
		if($_POST){
			$title = $_POST['title'];
			$area = $_POST['area'];
			$theDate = $_POST['theDate'];
			$company = $_POST['company'];
			$content = addslashes($_POST['content']);
			$status = $_POST['status'];
			$createTime = date('Y-m-d H:i:s');

			$DB->insert('JoinUs', 'title,area,theDate,company,content,status,createTime', "'$title','$area','$theDate','$company','$content','$status','$createTime'", 'index.php?app=manage&controller=JoinUs&action=listJoinUs');exit;
			
			
		}
	
		$data['test'] = ""; 
		$this->showTemplate('manage/addJoinUs', $data); 
	}
	
	
	public function editJoinUs(){
		$DB = $this->load('mysql',true);//数据库类
		$id = $_GET['id']?$_GET['id']:'';
			
		$Row = $DB->selectRow('JoinUs', "*", "id='$id'");
		$data['Row'] = $Row; 
		
		
		if($_POST){
			$id = $_POST['id'];
			$title = $_POST['title'];
			$area = $_POST['area'];
			$theDate = $_POST['theDate'];
			$company = $_POST['company'];
			$content = addslashes($_POST['content']);
			$status = $_POST['status'];
			$createTime = date('Y-m-d H:i:s');
			
			$DB->update('JoinUs', "title='$title',area='$area',theDate='$theDate',company='$company',content='$content',status='$status',createTime='$createTime'", 'id='.$id,'index.php?app=manage&controller=JoinUs&action=listJoinUs');exit;
			
			
		}
		
		
		$this->showTemplate('manage/editJoinUs', $data); 
	}
	
	
	public function deleteJoinUs(){
		$DB = $this->load('mysql',true);//数据库类
		
		
		$id = $_GET['id']?$_GET['id']:'';
		
		if($id){
			$DB->delete('JoinUs', "id='$id'", $url = 'index.php?app=manage&controller=JoinUs&action=listJoinUs');
			
		}
	}
	
	
	
}

