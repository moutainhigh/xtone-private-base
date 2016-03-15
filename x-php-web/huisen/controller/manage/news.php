<?php

class news extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//案例列表
	public function listNews() {
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		//获取翻页列表。
		$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
		//获取总记录数。
		$totalnum = array();
		$query = mysql_query("SELECT count(*) FROM news");
		$num = mysql_fetch_row($query);
		$totalnum = $num[0];
		//计算总页数。
		$limit = 20;//每页显示的数量。
		$totalpages = ceil($totalnum/$limit);
		
		$newsType = $DB->selectAll('newsType', "*", '', "");
		$data['newsType'] = $newsType; 
		
		$data['data'] = $DB->selectAll('news', "*", " nid>0 order by nid desc", ($page-1)*$limit.','.$limit);
		$data['page'] = $Func->page($totalnum,$limit);
		
		if($_POST){
			$ntid = $_POST['ntid'];
			$ntitle = $_POST['ntitle'];
			
			if($ntid==""){
				$typeSelet = "" ;
			}else{
				 $typeSelet = " and ntype='$ntid'" ;
			}
			$ReRe = array();
			$data['data'] = $DB->selectAll('news', "*", "ntitle like '%$ntitle%' $typeSelet order by nid desc", "");
			$data['tid'] = $ntid;
			$data['ntitle'] = $ntitle;
		}

	
		$this->showTemplate('manage/listNews', $data); 
	}
	
	
	public function addNews(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		if($_POST){
			$ntitle = $_POST['ntitle'];
			$ntype = $_POST['ntype'];
			$ntName = $_POST['ntName'];
			$ninfor = $_POST['ninfor'];
			$ncontent = addslashes($_POST['ncontent']);
			$nstatus = $_POST['nstatus'];
			$ncreateTime = date('Y-m-d H:i:s');
			
			$DB->insert('news', 'ntitle,ntype,ntName,ninfor,ncontent,nstatus,ncreateTime', "'$ntitle','$ntype','$ntName','$ninfor','$ncontent','$nstatus','$ncreateTime'", 'index.php?app=manage&controller=news&action=listNews');exit;
			
			
		}
	
		$newsType = $DB->selectAll('newsType', "*", '', "");
		$data['newsType'] = $newsType; 
		$this->showTemplate('manage/addNews', $data); 
	}
	
	
	public function editNews(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		$nid = $_GET['nid']?$_GET['nid']:'';
			
			
		$Row = $DB->selectRow('news', "*", "nid='$nid'");
		$data['Row'] = $Row; 
		
		$newsType = $DB->selectAll('newsType', "*", '', "");
		$data['newsType'] = $newsType; 
		
		
		if($_POST){
			$nid = $_POST['nid'];
			$ntitle = $_POST['ntitle'];
			$ntype = $_POST['ntype'];
			$ntName = $_POST['ntName'];
			$ninfor = $_POST['ninfor'];
			$ncontent = addslashes($_POST['ncontent']);
			$nstatus = $_POST['nstatus'];
			$ncreateTime = date('Y-m-d H:i:s');
			
			$DB->update('news', "ntitle='$ntitle',ntype='$ntype',ntName='$ntName',ninfor='$ninfor',ncontent='$ncontent',nstatus='$nstatus',ncreateTime='$ncreateTime'", 'nid='.$nid,'index.php?app=manage&controller=news&action=listNews');exit;
			
			
		}
		
		
		$this->showTemplate('manage/editNews', $data); 
	}
	
	
	public function deleteNews(){
		$DB = $this->load('mysql',true);//数据库类
		$Func = $this->load('func',false);//分页函数类
		
		
		$nid = $_GET['nid']?$_GET['nid']:'';
		
		if($nid){
			$DB->delete('news', "nid='$nid'", $url = 'index.php?app=manage&controller=news&action=listNews');
			
		}
	}
	
	
	
}

