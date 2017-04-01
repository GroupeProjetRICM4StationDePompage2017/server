<?php

	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');
	
	require_once('sql.php');
	require_once('MaBD.php');
	if(isset($_POST["login"]) and isset($_POST["mdp"]))
	{
		$sql = new SQL(MaBD::getInstance());
		$existe = $sql->identification($_POST['login'],$_POST['mdp']);
		if($existe!=NULL)
		{
			echo '[{"message":"True","value":'.$existe.'}]';
			
		}
		else
		{
			echo '[{"message":"False", "erreur":"Probleme vous n\'appartenez pas à la base ou votre mot de passe est faux"}]';
		}
	}
	else{echo '[{"message":"False", "erreur":"Problème champ manquant : login('.isset($_POST["login"]).'), mdp('.isset($_POST["mdp"]).')"}]';}

?>
