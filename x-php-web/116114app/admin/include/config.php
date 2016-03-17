<?php
error_reporting(0);
include "sqlserver.php";
date_default_timezone_set('PRC');

global $sqlsrv;
$sqlsrv = new sqlserver();
global $con;
$con = $sqlsrv->connent();



?>