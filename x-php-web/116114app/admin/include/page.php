<?php
/**
 * 分页函数----(记录数,总页数,有当前页,首页,上一页,中间页,下一页,末页,选择页)  的分页函数。
 *
 * @param int $count 总记录数
 * @param int $per  每页显示多少条
 * 
 * @return string
 */
function page($count, $per)
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
		
		$str .= "总共有 <strong style='color:red;>{$count}</strong> 条记录，&nbsp;共 <strong style='color:red;>{$total}</strong> 页，&nbsp;每页显示 <strong style='color:red;'>{$per}</strong> 条，&nbsp;当前是第 <strong style='color:red;'>{$pn}</strong> 页";
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


?>