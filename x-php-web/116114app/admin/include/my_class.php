<?php

/**
*  sql server class
*	
*/
class mys
{

	static $in_charset = "UTF-8";
	static $out_charset = "GB2312";

	static function setChar(&$arr){
		switch (gettype($arr)) {
			case 'array':
				foreach ($arr as $key => $value) {
					$arr[$key] = mys::setChar($value);
				}
				break;
			case 'string':
				$arr = iconv(mys::$in_charset, mys::$out_charset, $arr);
				break;
			default:
				break;
			}
		return $arr;
	}
}

?>