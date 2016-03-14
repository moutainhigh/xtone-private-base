<?php
error_reporting(1);
/**
* 作者:	林坤源 lamson
* 最后更新:	2010-01-20
* 实例化：Vdcode($id = 'yzcode', $len = 4, $type = 0, $width = 58, $height = 22, $wordleft = 5, $wordtop = 3, $bg1 = 230, $bg2 = 230, $bg3 = 230)
* 功能：PHP 验证码类，只支持PHP5
* 参数：
	$id: session ID
	$len:	验证码长度
	$type:	字符集
			0	数字和小写字母和大写字母的混合
			1	数字
			2	小写字母
			3	大写字母
	$width:	画布宽
	$height:	画布高
	$wordleft:	文字左边距
	$wordtop:	文字上边距
	$bg1:	设置背景颜色 （最高255）
	$bg2:	设置背景颜色 （最高255）
	$bg3:	设置背景颜色 （最高255）
* 应用
	$code = new Vdcode('yzcode');
	$code->outImg();	//显示图片
*/
class Vdcode
{
	public $sessionCode;
	public $width;
	public $height;
	public $wordLeft;
	public $wordTop;
	//验证码字符集方案
	private $_chars = array(1 => '0123456789', 2 => 'abcdefghijklmnopqrstuvwxyz', 3 => 'ABCDEFGHIJKLMNOPQRSTUVWXYZ');	
	private $_char;	//应用中的验证码字符集
	public $charlen;	//验证码 字符长度
	public $bgcolor1;
	public $bgcolor2;
	public $bgcolor3;
	
	function __construct($id = 'yzcode', $len = 4, $type = 0, $width = 58, $height = 22, $wordleft = 5, $wordtop = 3, $bg1 = 230, $bg2 = 230, $bg3 = 230)
	{
		$this->_char = $this->_chars[0] = $this->_chars[3].$this->_chars[1].$this->_chars[2];
		$this->setCharLen($len);
		$this->setCharType($type);
		$this->setSession($id);
		$this->setSize($width, $height, $wordleft, $wordtop);
		$this->setBgColor($bg1, $bg2, $bg3);
	}
	//设置验证码 字符长度
	function setCharLen($len)
	{
		$this->charlen = $len;
	}
	//设置应用中的验证码字符集
	function setCharType($type)
	{
		if($type >= 0 && $type <= 2)
		{
			$this->_char = $this->_chars[$type];
		}
	}
	//生成session
	function setSession($id)
	{
		session_start();
		session_register($id);
		while(strlen($code) < $this->charlen)
		{
			$code .=  substr($this->_char, (rand()%(strlen($this->_char))), 1);
		}
		$_SESSION[$id] = $this->sessionCode = $code;
	}
	//设置画布大小
	function setSize($width = 58, $height = 22, $wordleft = 5, $wordtop = 3)
	{
		$this->width = $width;
		$this->height = $height;
		$this->wordLeft = $wordleft;
		$this->wordTop = $wordtop;				
	}
	//设置背景颜色
	function setBgColor($bg1 = 250, $bg2 = 250, $bg3 = 250)
	{
		$this->bgcolor1 = $bg1;
		$this->bgcolor2 = $bg2;
		$this->bgcolor3 = $bg3;
	}
	
	private function _createImage()
	{
		if(function_exists('imagecreate') && function_exists('imagecolorset') && function_exists('imagecopyresized') && function_exists('imagecolorallocate') && function_exists('imagesetpixel') && function_exists('imagechar') && function_exists('imagecreatefromgif') && function_exists('imagepng'))
		{
			//新建一个基于调色板的图像
			$im = imagecreate($this->width, $this->height);
			//为一幅图像分配背景颜色
			$backgroundcolor = imagecolorallocate ($im, $this->bgcolor1, $this->bgcolor2, $this->bgcolor3);
			for($i = 1; $i <= $this->charlen; ++$i)
			{
				$numorder[] = $i;
			}
			//打乱（随机排列单元的顺序）一个数组
			shuffle($numorder);
			$numorder = array_flip($numorder);	//交换数组中的键和值
			for($i = 1; $i <= $this->charlen; ++$i)
			{
				$x = $numorder[$i] * 13 + mt_rand(0, $this->charlen) - 2;
				$y = mt_rand(0, 3);
				$text_color = imagecolorallocate($im, mt_rand(50, 255), mt_rand(50, 128), mt_rand(50, 255));
				//水平地画一个字符
				imagechar($im, 5, $x + $this->wordLeft, $y + $this->wordTop, $this->sessionCode[$numorder[$i]], $text_color);
			}
			$linenums = mt_rand(5, 15);
			//添加杂线
			for($i = 0; $i <= $linenums; ++$i)
			{
				$linecolor = imagecolorallocate($im, mt_rand(0, 255), mt_rand(0, 255), mt_rand(0, 255));
				$linex = mt_rand(0, $this->width); //x
				$liney = mt_rand(0, $this->height);//y
				//画一条线段
				imageline($im, $linex, $liney, $linex + mt_rand(0, 4) - 2, $liney + mt_rand(0, 4) - 2, $linecolor);
			}
			//添加杂点
			for($i = 0; $i <= 15; ++$i)
			{
				$pointcolor = imagecolorallocate($im, mt_rand(50, 255), mt_rand(50, 255), mt_rand(50, 255));
				// 在 $im 图像中用 color 颜色在 x，y 坐标（图像左上角为 0，0）上画一个点。 
				imagesetpixel($im, mt_rand(0, $this->width), mt_rand(0, $this->height), $pointcolor);
			}
			$bordercolor = imagecolorallocate($im , 150, 150, 150);
			//画一个矩形
			imagerectangle($im, 0, 0, $this->width-1, $this->height-1, $bordercolor);
			header ('Content-type: image/png');
			imagepng($im);
			imagedestroy($im);
		}	
	}
	//输出验证码图片
	function outImg()
	{
		$this->_createImage();
	}
}
	$code = new Vdcode('yzcode', 4, 2, 58, 20, 5, 0, 231, 244, 253);
	$code->outImg();	//显示图片
?>