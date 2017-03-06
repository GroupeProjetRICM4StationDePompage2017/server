<?php

	require_once('sql.php');
	require_once('MaBD.php');

	
	if(isset($_POST["login"]) and isset($_POST["mdp"]) and isset($_POST["levelrequire"]))
	{
		$sql = new SQL(MaBD::getInstance());
			
		if($sql->getOrder()==NULL)
		{
			echo "ADD";

			$ok = $sql->addOrder(1,$_POST['login'],$_POST['mdp'],$_POST['levelrequire']);			
		}
		else
		{
			echo "UPDATE";
			$ok = $sql->updateOrder(1,$_POST['login'],$_POST['mdp'],$_POST['levelrequire']);
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