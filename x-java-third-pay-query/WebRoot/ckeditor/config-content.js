/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
		config.font_names = '微软雅黑/Microsoft YaHei;宋体/SimSun;新宋体/NSimSun;仿宋/FangSong;楷体/KaiTi;仿宋_GB2312/FangSong_GB2312;'+
			'楷体_GB2312/KaiTi_GB2312;黑体/SimHei;微软正黑/Microsoft JhengHei;'+
			'Arial Black/Arial Black;'+ config.font_names;
		config.height = "400";//文本域高度
		config.uiColor = '#5599ff';
		config.resize_enabled = false;
		config.toolbarCanCollapse = true;
		config.toolbarStartupExpanded = true;
};
