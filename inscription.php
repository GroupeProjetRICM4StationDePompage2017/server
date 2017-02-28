<?php

	require_once('sql.php');
	require_once('MaBD.php');

	
	$sql = new SQL(MaBD::getInstance());
	if(isset($_POST["login"]) and isset($_POST["mdp"]) and isset($_POST["nom"]) and isset($_POST["prenom"]) and isset($_POST["jar"]))
	{
		$res = $sql->addUser($_POST["login"],$_POST["mdp"],$_POST["nom"],$_POST["prenom"],$_POST["jar"]);
		if($res){echo '[{"message":True}]';}
		else{echo '[{"message":False}]';}
	}
	else
	{
		echo '[{"message":False}]';
	}


?>