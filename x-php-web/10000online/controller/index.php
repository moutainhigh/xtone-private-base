<?php

class index extends Controller {
        
        public function __construct() {
                parent::__construct();
        }

        public function index() {
			$DB = $this->load('mysql',true);//数据库类
				
			$data['games'] = $DB->selectAll('games', "*", 'status=1 order by orderSort,id desc', "6");
			$data['hotgames'] = $DB->selectAll('games', "*", 'status=1 and isHome=1 order by orderSort,id desc', "3");
			$data['hotnews'] = $DB->selectAll('news', "nid,ntitle,ntName", 'nstatus=1 order by nid desc', "13");
			$data['news'] = $DB->selectAll('news', "nid,ntitle,ntName", 'nstatus=1 and ntype=3 order by nid desc', "7");
			$data['banner'] = $DB->selectAll('games', "*", 'status=1 and isBanner=1 order by orderSort desc', "5");
			$data['JoinUs'] = $DB->selectAll('JoinUs', "*", 'status=1 order by id desc', "5");

			$data['game1'] = $DB->selectAll('games', "*", 'gType=5 and status=1 order by orderSort,id desc', "7");
			$data['game2'] = $DB->selectAll('games', "*", 'gType=4 and status=1 order by orderSort,id desc', "7");
			$data['game3'] = $DB->selectAll('games', "*", 'gType=7 and status=1 order by orderSort,id desc', "7");
			$data['game4'] = $DB->selectAll('games', "*", 'gType=8 and status=1 order by orderSort,id desc', "7");
			$data['game5'] = $DB->selectAll('games', "*", 'gType=10 and status=1 order by orderSort,id desc', "7");
			$data['game6'] = $DB->selectAll('games', "*", 'gType=12 and status=1 order by orderSort,id desc', "7");
			$this->showTemplate('index', $data); 	
        }
}

