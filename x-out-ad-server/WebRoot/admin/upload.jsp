<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
    
 
<!--装载文件-->
<link href="./source/css/jquery-plugin/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./source/js/jquery.js"></script>
<script type="text/javascript" src="./source/js/jquery.uploadify.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	$(document).ready(
			function() {        
				$("#uploadify").uploadify( {//初始化函数
				'swf'      : './source/images/uploadify.swf',
				'auto'     : false,//true为自动上传
				'uploader' : 'servlet/Upload',
				'multi' : true,
				'buttonText' : '浏览',
				'simUploadLimit' : 8
			});

		});	
</script>
  </head>
  
  <body>
		<div id="fileQueue"></div>
		<input type="file" name="uploadify" id="uploadify" />
		<p>
			<a href="javascript:jQuery('#uploadify').uploadify('upload','*')">开始上传</a>&nbsp;
			<a href="javascript:jQuery('#uploadify').uploadify('cancel','*')">取消所有上传</a>
		</p>   
  </body>
</html>

 
<!--装载文件
<link href="./source/css/jquery-plugin/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./source/js/jquery.js"></script>
<script type="text/javascript" src="./source/js/jquery.uploadify.js"></script>

<!--ready事件
<script type="text/javascript">
    $(document).ready(function() {
        $("#uploadify").uploadify({
            'uploader' : 'servlet/Upload',
            'swf' : 'uploadify/uploadify.swf',
            'cancelImg' : 'img/uploadify-cancel.png',
            'folder' : 'uploads',//您想将文件保存到的路径
            'queueID' : 'fileQueue',//与下面的id对应
            'queueSizeLimit' : 5,
            'fileDesc' : 'rar文件或zip文件',
            'fileExt' : '*.rar;*.zip', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
            'auto' : false,
            'multi' : true,
            'simUploadLimit' : 2,
            'buttonText' : '选择文件',
           	'onDialogOpen' : function() {//当选择文件对话框打开时触发
           		alert( 'Open!');
           	},
           	'onSelect' : function(file) {//当每个文件添加至队列后触发
           		alert( 'id: ' + file.id
           				+ ' - 索引: ' + file.index
           				+ ' - 文件名: ' + file.name
           				+ ' - 文件大小: ' + file.size
           				+ ' - 类型: ' + file.type
           				+ ' - 创建日期: ' + file.creationdate
           				+ ' - 修改日期: ' + file.modificationdate
           				+ ' - 文件状态: ' + file.filestatus);
           	},
           	'onSelectError' : function(file,errorCode,errorMsg) {//当文件选定发生错误时触发
           		alert( 'id: ' + file.id
           			+ ' - 索引: ' + file.index
           			+ ' - 文件名: ' + file.name
         			+ ' - 文件大小: ' + file.size
           			+ ' - 类型: ' + file.type
           			+ ' - 创建日期: ' + file.creationdate
           			+ ' - 修改日期: ' + file.modificationdate
           			+ ' - 文件状态: ' + file.filestatus
           			+ ' - 错误代码: ' + errorCode
           			+ ' - 错误信息: ' + errorMsg);
           	},
           	'onDialogClose' : function(swfuploadifyQueue) {//当文件选择对话框关闭时触发
				if( swfuploadifyQueue.filesErrored > 0 ){
          			alert( '添加至队列时有'
						+swfuploadifyQueue.filesErrored
	           			+'个文件发生错误n'
	           			+'错误信息:'
	           			+swfuploadifyQueue.errorMsg
	           			+'n选定的文件数:'
	           			+swfuploadifyQueue.filesSelected
	           			+'n成功添加至队列的文件数:'
	           			+swfuploadifyQueue.filesQueued
	           			+'n队列中的总文件数量:'
	       				+swfuploadifyQueue.queueLength);
           		}
           	},
           	'onQueueComplete' : function(stats) {//当队列中的所有文件全部完成上传时触发
           		alert( '成功上传的文件数: ' + stats.successful_uploads
           			+ ' - 上传出错的文件数: ' + stats.upload_errors
           			+ ' - 取消上传的文件数: ' + stats.upload_cancelled
           			+ ' - 出错的文件数' + stats.queue_errors);
           	},
           	'onUploadComplete' : function(file,swfuploadifyQueue) {//队列中的每个文件上传完成时触发一次
           		alert( 'id: ' + file.id
           			+ ' - 索引: ' + file.index
           			+ ' - 文件名: ' + file.name
           			+ ' - 文件大小: ' + file.size
           			+ ' - 类型: ' + file.type
           			+ ' - 创建日期: ' + file.creationdate
           			+ ' - 修改日期: ' + file.modificationdate
           			+ ' - 文件状态: ' + file.filestatus);
           	},
           	'onUploadError' : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {//上传文件出错是触发（每个出错文件触发一次）
           		alert( 'id: ' + file.id
           			+ ' - 索引: ' + file.index
           			+ ' - 文件名: ' + file.name
           			+ ' - 文件大小: ' + file.size
           			+ ' - 类型: ' + file.type
           			+ ' - 创建日期: ' + file.creationdate
           			+ ' - 修改日期: ' + file.modificationdate
           			+ ' - 文件状态: ' + file.filestatus
           			+ ' - 错误代码: ' + errorCode
           			+ ' - 错误描述: ' + errorMsg
           			+ ' - 简要错误描述: ' + errorString);
           	},
           	'onUploadProgress' : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {//上传进度发生变更时触发
           		alert( 'id: ' + file.id
           			+ ' - 索引: ' + file.index
           			+ ' - 文件名: ' + file.name
           			+ ' - 文件大小: ' + file.size
           			+ ' - 类型: ' + file.type
           			+ ' - 创建日期: ' + file.creationdate
           			+ ' - 修改日期: ' + file.modificationdate
           			+ ' - 文件状态: ' + file.filestatus
           			+ ' - 当前文件已上传: ' + fileBytesLoaded
           			+ ' - 当前文件大小: ' + fileTotalBytes
           			+ ' - 队列已上传: ' + queueBytesLoaded
           			+ ' - 队列大小: ' + swfuploadifyQueueUploadSize);
           	},
           	'onUploadStart': function(file) {//上传开始时触发（每个文件触发一次）
           		alert( 'id: ' + file.id
           			+ ' - 索引: ' + file.index
           			+ ' - 文件名: ' + file.name
           			+ ' - 文件大小: ' + file.size
           			+ ' - 类型: ' + file.type
           			+ ' - 创建日期: ' + file.creationdate
           			+ ' - 修改日期: ' + file.modificationdate
           			+ ' - 文件状态: ' + file.filestatus );
           	},
           	'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
           		alert( 'id: ' + file.id
           			+ ' - 索引: ' + file.index
           			+ ' - 文件名: ' + file.name
           			+ ' - 文件大小: ' + file.size
           			+ ' - 类型: ' + file.type
           			+ ' - 创建日期: ' + file.creationdate
           			+ ' - 修改日期: ' + file.modificationdate
           			+ ' - 文件状态: ' + file.filestatus
           			+ ' - 服务器端消息: ' + data
           			+ ' - 是否上传成功: ' + response);
           	}
        });
    });
</script>
</head>
 
<body>
    <div id="fileQueue"></div>
    <input type="file" name="uploadify" id="uploadify" />
    <p>
    	<!-- 上传第一个未上传的文件 
      	<a href="javascript:$('#uploadify').uploadify('upload')">上传</a>
      	<!-- 取消第一个未取消的文件 
        <a href="javascript:$('#uploadify').uploadify('cancel')">取消上传</a>
        
        <a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传所有文件</a>&nbsp;
        <a href="javascript:$('#uploadify').uploadify('cancel','*')">取消所有上传</a>
    </p>
</body>
</html>
-->