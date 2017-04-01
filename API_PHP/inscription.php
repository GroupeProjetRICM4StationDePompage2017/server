<?php

	require_once('sql.php');
	require_once('MaBD.php');

	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');
	
	$sql = new SQL(MaBD::getInstance());
	if(isset($_POST["login"]) and isset($_POST["mdp"]) and isset($_POST["nom"]) and isset($_POST["prenom"]) and isset($_POST["jar"]))
	{
		if($_POST["login"]=="" or $_POST["mdp"]=="" or $_POST["nom"]=="" or $_POST["prenom"]=="")
		{echo '[{"message":"False", "erreur":"L\'un des champs est vide "}]';}
		//$res = $sql->addUser($_POST["login"],$_POST["mdp"],$_POST["nom"],$_POST["prenom"],$_POST["jar"]);
		$res = $sql->addUser($_POST["login"],$_POST["mdp"],$_POST["nom"],$_POST["prenom"],'False');
		if($res){echo '[{"message":"True"}]';}
		else{echo '[{"message":"False", "erreur":"Vous exitez déjà dans la base de donnée"}]';}
	}
	else
	{
		echo '[{"message":"False", "erreur":"Problème champ manquant"}]';
	}


?>
