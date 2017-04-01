<?php

	require_once('sql.php');
	require_once('MaBD.php');

	$sql = new SQL(MaBD::getInstance());

	if(isset($_POST["id"]) and isset($_POST["date"]) and isset($_POST["time"]) and isset($_POST["level"]) and isset($_POST["state"]) and isset($_POST["levelb"]))
	{
		$res = $sql->addData($_POST["id"],$_POST["date"],$_POST["time"],$_POST["level"],$_POST["state"],$_POST["levelb"]);
		if($res)
		{
			echo '[{"message":"True"}]';
		}
		else{echo '[{"message":"False", "erreur":"Problème lors de l\'insertion des données"}]';}
	}
	else
	{
		echo '[{"message":"False", "erreur":"Problème champ manquant"}]';
	}
	

?>