<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
    
 
<!--װ���ļ�-->
<link href="./source/css/jquery-plugin/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./source/js/jquery.js"></script>
<script type="text/javascript" src="./source/js/jquery.uploadify.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	$(document).ready(
			function() {        
				$("#uploadify").uploadify( {//��ʼ������
				'swf'      : './source/images/uploadify.swf',
				'auto'     : false,//trueΪ�Զ��ϴ�
				'uploader' : 'servlet/Upload',
				'multi' : true,
				'buttonText' : '���',
				'simUploadLimit' : 8
			});

		});	
</script>
  </head>
  
  <body>
		<div id="fileQueue"></div>
		<input type="file" name="uploadify" id="uploadify" />
		<p>
			<a href="javascript:jQuery('#uploadify').uploadify('upload','*')">��ʼ�ϴ�</a>&nbsp;
			<a href="javascript:jQuery('#uploadify').uploadify('cancel','*')">ȡ�������ϴ�</a>
		</p>   
  </body>
</html>

 
<!--װ���ļ�
<link href="./source/css/jquery-plugin/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./source/js/jquery.js"></script>
<script type="text/javascript" src="./source/js/jquery.uploadify.js"></script>

<!--ready�¼�
<script type="text/javascript">
    $(document).ready(function() {
        $("#uploadify").uploadify({
            'uploader' : 'servlet/Upload',
            'swf' : 'uploadify/uploadify.swf',
            'cancelImg' : 'img/uploadify-cancel.png',
            'folder' : 'uploads',//���뽫�ļ����浽��·��
            'queueID' : 'fileQueue',//�������id��Ӧ
            'queueSizeLimit' : 5,
            'fileDesc' : 'rar�ļ���zip�ļ�',
            'fileExt' : '*.rar;*.zip', //���ƿ��ϴ��ļ�����չ�������ñ���ʱ��ͬʱ����fileDesc
            'auto' : false,
            'multi' : true,
            'simUploadLimit' : 2,
            'buttonText' : 'ѡ���ļ�',
           	'onDialogOpen' : function() {//��ѡ���ļ��Ի����ʱ����
           		alert( 'Open!');
           	},
           	'onSelect' : function(file) {//��ÿ���ļ���������к󴥷�
           		alert( 'id: ' + file.id
           				+ ' - ����: ' + file.index
           				+ ' - �ļ���: ' + file.name
           				+ ' - �ļ���С: ' + file.size
           				+ ' - ����: ' + file.type
           				+ ' - ��������: ' + file.creationdate
           				+ ' - �޸�����: ' + file.modificationdate
           				+ ' - �ļ�״̬: ' + file.filestatus);
           	},
           	'onSelectError' : function(file,errorCode,errorMsg) {//���ļ�ѡ����������ʱ����
           		alert( 'id: ' + file.id
           			+ ' - ����: ' + file.index
           			+ ' - �ļ���: ' + file.name
         			+ ' - �ļ���С: ' + file.size
           			+ ' - ����: ' + file.type
           			+ ' - ��������: ' + file.creationdate
           			+ ' - �޸�����: ' + file.modificationdate
           			+ ' - �ļ�״̬: ' + file.filestatus
           			+ ' - �������: ' + errorCode
           			+ ' - ������Ϣ: ' + errorMsg);
           	},
           	'onDialogClose' : function(swfuploadifyQueue) {//���ļ�ѡ��Ի���ر�ʱ����
				if( swfuploadifyQueue.filesErrored > 0 ){
          			alert( '���������ʱ��'
						+swfuploadifyQueue.filesErrored
	           			+'���ļ���������n'
	           			+'������Ϣ:'
	           			+swfuploadifyQueue.errorMsg
	           			+'nѡ�����ļ���:'
	           			+swfuploadifyQueue.filesSelected
	           			+'n�ɹ���������е��ļ���:'
	           			+swfuploadifyQueue.filesQueued
	           			+'n�����е����ļ�����:'
	       				+swfuploadifyQueue.queueLength);
           		}
           	},
           	'onQueueComplete' : function(stats) {//�������е������ļ�ȫ������ϴ�ʱ����
           		alert( '�ɹ��ϴ����ļ���: ' + stats.successful_uploads
           			+ ' - �ϴ�������ļ���: ' + stats.upload_errors
           			+ ' - ȡ���ϴ����ļ���: ' + stats.upload_cancelled
           			+ ' - ������ļ���' + stats.queue_errors);
           	},
           	'onUploadComplete' : function(file,swfuploadifyQueue) {//�����е�ÿ���ļ��ϴ����ʱ����һ��
           		alert( 'id: ' + file.id
           			+ ' - ����: ' + file.index
           			+ ' - �ļ���: ' + file.name
           			+ ' - �ļ���С: ' + file.size
           			+ ' - ����: ' + file.type
           			+ ' - ��������: ' + file.creationdate
           			+ ' - �޸�����: ' + file.modificationdate
           			+ ' - �ļ�״̬: ' + file.filestatus);
           	},
           	'onUploadError' : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {//�ϴ��ļ������Ǵ�����ÿ�������ļ�����һ�Σ�
           		alert( 'id: ' + file.id
           			+ ' - ����: ' + file.index
           			+ ' - �ļ���: ' + file.name
           			+ ' - �ļ���С: ' + file.size
           			+ ' - ����: ' + file.type
           			+ ' - ��������: ' + file.creationdate
           			+ ' - �޸�����: ' + file.modificationdate
           			+ ' - �ļ�״̬: ' + file.filestatus
           			+ ' - �������: ' + errorCode
           			+ ' - ��������: ' + errorMsg
           			+ ' - ��Ҫ��������: ' + errorString);
           	},
           	'onUploadProgress' : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {//�ϴ����ȷ������ʱ����
           		alert( 'id: ' + file.id
           			+ ' - ����: ' + file.index
           			+ ' - �ļ���: ' + file.name
           			+ ' - �ļ���С: ' + file.size
           			+ ' - ����: ' + file.type
           			+ ' - ��������: ' + file.creationdate
           			+ ' - �޸�����: ' + file.modificationdate
           			+ ' - �ļ�״̬: ' + file.filestatus
           			+ ' - ��ǰ�ļ����ϴ�: ' + fileBytesLoaded
           			+ ' - ��ǰ�ļ���С: ' + fileTotalBytes
           			+ ' - �������ϴ�: ' + queueBytesLoaded
           			+ ' - ���д�С: ' + swfuploadifyQueueUploadSize);
           	},
           	'onUploadStart': function(file) {//�ϴ���ʼʱ������ÿ���ļ�����һ�Σ�
           		alert( 'id: ' + file.id
           			+ ' - ����: ' + file.index
           			+ ' - �ļ���: ' + file.name
           			+ ' - �ļ���С: ' + file.size
           			+ ' - ����: ' + file.type
           			+ ' - ��������: ' + file.creationdate
           			+ ' - �޸�����: ' + file.modificationdate
           			+ ' - �ļ�״̬: ' + file.filestatus );
           	},
           	'onUploadSuccess' : function(file,data,response) {//�ϴ����ʱ������ÿ���ļ�����һ�Σ�
           		alert( 'id: ' + file.id
           			+ ' - ����: ' + file.index
           			+ ' - �ļ���: ' + file.name
           			+ ' - �ļ���С: ' + file.size
           			+ ' - ����: ' + file.type
           			+ ' - ��������: ' + file.creationdate
           			+ ' - �޸�����: ' + file.modificationdate
           			+ ' - �ļ�״̬: ' + file.filestatus
           			+ ' - ����������Ϣ: ' + data
           			+ ' - �Ƿ��ϴ��ɹ�: ' + response);
           	}
        });
    });
</script>
</head>
 
<body>
    <div id="fileQueue"></div>
    <input type="file" name="uploadify" id="uploadify" />
    <p>
    	<!-- �ϴ���һ��δ�ϴ����ļ� 
      	<a href="javascript:$('#uploadify').uploadify('upload')">�ϴ�</a>
      	<!-- ȡ����һ��δȡ�����ļ� 
        <a href="javascript:$('#uploadify').uploadify('cancel')">ȡ���ϴ�</a>
        
        <a href="javascript:$('#uploadify').uploadify('upload','*')">��ʼ�ϴ������ļ�</a>&nbsp;
        <a href="javascript:$('#uploadify').uploadify('cancel','*')">ȡ�������ϴ�</a>
    </p>
</body>
</html>
-->