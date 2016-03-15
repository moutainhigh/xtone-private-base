<?php
class Game
{
    /*===列表相关===*/
    function GList($con,$pageSize,$pageIndex,$type)//
    {
            
            $pageIndex = $pageIndex-1;
            $page = $pageIndex*$pageSize;
			
            if($type==0){
                $typeSelet = "" ;
            }else{
                 $typeSelet = " and ga_type='$type'" ;
            }
			
			//SELECT TOP PageSize * FROM LeaveWordView WHERE ID NOT IN(SELECT TOP (PageIndex-1)*PageSize ID FROM LeaveWordView)
			$selectSQL="select TOP $pageSize ga_id as gameId,ga_name as gameName,ga_icon as gameIcon,ga_apkLink as gameUrl,ga_http as gameHttp,ga_versionNane as gameversionNane,provider.pr_company as gameCom,ga_apkSize as gameSize,ga_type as gameType ,(SELECT avg(co_score) from comment where co_gameId=ga_id) as gameScore from game left join provider on ga_prId=pr_id where ga_isValid='Yes' $typeSelet AND ga_id NOT IN(SELECT TOP $page ga_id FROM game)";
			$query = sqlsrv_query($con,$selectSQL);
			while($row = sqlsrv_fetch_array($query)){
				$SeSQL[] = $row;
			}
            return $SeSQL;
    }
    
    function GListCount($con,$type){
        if($type==0){
			$typeSelet = "" ;
		}else{
			 $typeSelet = " where ga_type='$type'" ;
		}
			
        $CountSQL = "select count(*) from game $typeSelet";
		$query = sqlsrv_query($con,$CountSQL);
		$row = sqlsrv_fetch_array($query);
        return $row[0];
    }
    
      /*===列表相关===*/
    
    
    
    
     /*===详情相关===*/
    function GetInfo($con,$GameID){
        
        $selectSQL="select ga_id AS gameID,ga_name as gameName,ga_icon as gamelcon,ga_apkLink as gameUrl,ga_http as gameHttp,ga_version as gameVersion,ga_apkSize as gameSize,ga_introduce as gameIntro,ga_picture as gamePicture,provider.pr_name as gameCom from game LEFT JOIN provider on ga_prId=pr_id where ga_id='$GameID'";
        $query = sqlsrv_query($con,$selectSQL);
		$SeSQL = sqlsrv_fetch_array($query);

         return $SeSQL;
    }
     /*===详情相关===*/
    
    
    
    
      /*===喜欢推荐===*/
   function Like($con) {
        //SELECT TOP PageSize * FROM LeaveWordView WHERE ID NOT IN(SELECT TOP (PageIndex-1)*PageSize ID FROM LeaveWordView)
        $SQL = "select TOP 6 ga_id as gameId,ga_name as gameName,ga_icon as gameIcon,ga_apkLink as gameUrl,ga_http as gameHttp,ga_versionNane as gameversionNane,provider.pr_company as gameCom,ga_apkSize as gameSize,ga_type as gameType ,(SELECT avg(co_score) from comment where co_gameId=ga_id) as gameScore from game left join provider on ga_prId=pr_id where ga_isValid='Yes' order by ga_id desc";
		$query = sqlsrv_query($con,$SQL);
		while($row = sqlsrv_fetch_array($query)){
			$SeSQL[] = $row;
		}
        return $SeSQL;
    }
     /*===喜欢推荐===*/
	 
	 
	 
	 //返回可充值的游戏，返回信息有游戏ＩＤ，游戏名，包名，三部分
	  function GetCondition($con,$tabledata,$condition){
        
        $selectSQL="select ga_id AS gameID,ga_name as gameName,ga_bagName as bagName, ga_icon as gamelcon,ga_apkLink as gameUrl,ga_http as gameHttp,ga_version as gameVersion,ga_apkSize as gameSize,ga_introduce as gameIntro,ga_picture as gamePicture,provider.pr_name as gameCom from game LEFT JOIN provider on ga_prId=pr_id where $tabledata='$condition'";
        // $SeSQL = $db->fetchAll($selectSQL);
		$query = sqlsrv_query($con,$selectSQL);
		while($row = sqlsrv_fetch_array($query)){
			$SeSQL[] = $row;
		}
         return $SeSQL;
    }
	 
}

?>