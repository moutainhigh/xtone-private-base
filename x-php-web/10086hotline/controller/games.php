<?php

class games extends Controller {
        
        public function __construct() {
                parent::__construct();
        }

        public function index() {
			$DB = $this->load('mysql',true);//数据库类
			$Func = $this->load('func',false);//分页函数类//获取翻页列
			
			$tid = intval($_GET['tid']) ? intval($_GET['tid']) :0;
			if($tid>0){
				$sel = " AND gType='$tid'";
			}else{
				$sel = "";
			}
			
			$page = intval($_GET['pn']) ? intval($_GET['pn']) :1;
			//获取总记录数。
			$totalnum = array();
			$query = mysql_query("SELECT count(*) FROM games WHERE status=1 $sel");
			$num = mysql_fetch_row($query);
			$totalnum = $num[0];
			//计算总页数。
			$limit = 12;//每页显示的数量。
			$totalpages = ceil($totalnum/$limit);
			
			$data['games'] = $DB->selectAll('games', "*", " status=1 $sel and id>0 order by id desc", ($page-1)*$limit.','.$limit);
			$data['page'] = $Func->page($totalnum,$limit);
			
			$gType = $DB->selectAll('gType', "*", 'gt_status=1', "");
			$data['gType'] = $gType; 
			$data['hotgames'] = $DB->selectAll('games', "*", 'status=1 and isHome=1 order by id desc', "3");
			
			$data['tid'] = $tid; 
			
			$data['head'] = 'games';
			$this->showTemplate('games', $data); 
        }
		
		
		
		public function gamesDetail(){
			$DB = $this->load('mysql',true);//数据库类
			$id = $_GET['id']?$_GET['id']:'';
			
			$data['hotnews'] = $DB->selectAll('news', "nid,ntitle,ntName,ncreateTime", 'nstatus=1 order by nid desc', "6");
			$data['games'] = $DB->selectAll('games', "*", 'status=1 order by id desc', "3");
			
			$data['ArrRow'] = $DB->selectRow('games', "*", "status=1 AND id='$id'");
			$data['screenshot'] = explode("|",  $data['ArrRow']['screenshot']);
			$data['count'] = count($data['screenshot'],0);
			
			$data['head'] = 'games';
			$this->showTemplate('gamesDetail', $data);
		}
}

