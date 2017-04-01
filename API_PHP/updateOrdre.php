<?php

	require_once('sql.php');
	require_once('MaBD.php');
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');
	
	if(isset($_POST["login"]) and isset($_POST["mdp"]))
	{
		$sql = new SQL(MaBD::getInstance());
		$ok = false;
		if(isset($_POST["id"]))
		{
			$ok = $sql->executedOrder($_POST["id"],$_POST['login'],$_POST['mdp']);
		}
		else
		{	

			$ok = $sql->executedOrder(36,$_POST['login'],$_POST['mdp']);
		}
		if($ok)
		{
			echo '[{"message":"True"}]';
			
		}
		else
		{
			echo '[{"message":"False", "erreur":"Problème lors mise à jour des executions"}]';
		}
		
	}
	else
	{	
		echo '[{"message":"False", "erreur":"Problème champ manquant"}]';
	}
?>
