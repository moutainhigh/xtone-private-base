<?php

class Func{

function upload($files,$uploadpath){

	if($files['tmp_name']){
		$r=rand(1,100);
		$filename=date('YmdHis').$r.$this->get_filetype($files['name']);
		
		$target=$uploadpath.$filename;
		
		
		if(copy($files['tmp_name'],$target)){
			return $uploadpath.$filename;
		}
		else{
			$this->showError($files);
		}
	}
	return '';
}

function removeFile($filepath,$path){
	  if (!empty($filepath)&&file_exists('../'.$path.$filepath)) {
				unlink('../'.$path.$filepath);
				return true;
		}
		else{
			return false;
		}
}
 function showError($files)
  {
		 if (isset($files['error'])) 
		{
		
			$php_maxsize = ini_get('upload_max_filesize');
			$msg="";
			if ($files['error'] == 0)
		   {
				 if (!$this->check_img_type($files['type']))
				 {
					   $msg='上传的类型不对!';
				   }
			}
			elseif ($files['error'] == 1)
		   {
				  $msg="上传太大,最大为$php_maxsize";
			}
			
		}

  }

 function get_filetype($path)
{
	$pos = strrpos($path, '.');
	if ($pos !== false)
	{
		return substr($path, $pos);
	}
	else
	{
		return '';
	}
}
function check_img_type($imgtype)
{
	switch($imgtype){
	 case'image/pjpeg':
	 case'image/gif':
	 case'image/png':
	 case 'swf':
	 case 'apk':
	}
	return 1;
}





//翻页函数
function page($count, $per = 20)
	{
		//global $pn;
		$GLOBALS['pn'] = $pn = $_GET['pn'] ? $_GET['pn'] : 1;//当前的页码
		$total = ceil( ( $count > 0 ? $count : 1 ) / $per );
		 //除在pn以外的其他GET参数
		unset($_GET['pn']);
		foreach($_GET as $k=>$v)
		{
			$cs .= "&$k=$v";
			//$cs = $cs . "&$k=$v";	
		}
		
		$str .= "总共有 <strong>{$count}</strong> 条记录，&nbsp;共 <strong>{$total}</strong> 页，&nbsp;每页显示 <strong>{$per}</strong> 条，&nbsp;当前是第 <strong>{$pn}</strong> 页";
		if($pn == 1)
		{
			$str .= '&nbsp;&nbsp;首页&nbsp;&nbsp;上一页';	
		}else
		{
			$str .= ' <a href="?pn=1'.$cs.'">首页</a> <a href="?pn='.($pn-1).$cs.'">上一页</a>';	
		}
		
		if($pn==$total)
		{
			$str .= '&nbsp;&nbsp;下一页&nbsp;&nbsp;尾页&nbsp;&nbsp;';
		}else
		{
			$str .= ' <a href="?pn='.($pn+1).$cs.'">下一页</a> <a href="?pn='.$total.$cs.'">尾页</a>';	
		}
		$str .= '&nbsp;跳转到第 <select id="pageSelect" onchange="gotoPage(this.value)">';
		for($i=1; $i<=$total; $i++)
		{
			$str .= "<option value='$i'>$i</option>";	
		}
		$str .= '</select> 页';
$str .= <<< SCPT
<script>
document.getElementById('pageSelect').selectedIndex = $pn-1
function gotoPage(pn)
{
	window.location = "?pn=" + pn + "$cs";	
}
</script>
SCPT;
		return $str;
	}

}

?>