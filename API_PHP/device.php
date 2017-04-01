<?php

	require_once('sql.php');
	require_once('MaBD.php');

	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');
	
	$sql = new SQL(MaBD::getInstance());
	$res = $sql->getDevice();
	if($res==NULL){echo '[{"message":"False", "erreur":"Pas de device disponible"}]';}
	else{echo $res;}
?>

