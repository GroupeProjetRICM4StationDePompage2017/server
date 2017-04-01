<?php

	require_once('sql.php');
	require_once('MaBD.php');
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');
	
	if(isset($_POST["login"]) and isset($_POST["mdp"]) and isset($_POST["levelrequire"]))
	{
		$sql = new SQL(MaBD::getInstance());
		$ok = false;
		if(isset($_POST["id"]))
		{
			if($sql->getOrder($_POST["id"])==NULL)
			{

				$ok = $sql->addOrder($_POST["id"],$_POST['login'],$_POST['mdp'],$_POST['levelrequire']);			
			}
			else
			{
				$ok = $sql->updateOrder($_POST["id"],$_POST['login'],$_POST['mdp'],$_POST['levelrequire']);
			}
		}
		else
		{	
			if($sql->getOrder(36)==NULL)
			{

				$ok = $sql->addOrder(36,$_POST['login'],$_POST['mdp'],$_POST['levelrequire']);			
			}
			else
			{
				$ok = $sql->updateOrder(36,$_POST['login'],$_POST['mdp'],$_POST['levelrequire']);
			}
		}
		if($ok)
		{
			echo '[{"message":"True"}]';
			
		}
		else
		{
			echo '[{"message":"False", "erreur":"Problème lors de l\'insertion ou du update"}]';
		}
		
	}
	else
	{	
		echo '[{"message":"False", "erreur":"Problème champ manquant"}]';
	}
?>
