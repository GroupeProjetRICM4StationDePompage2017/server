<?php

	require_once('sql.php');
	require_once('MaBD.php');

	$sql = new SQL(MaBD::getInstance());
	$existe = $sql->identification($_POST['login'],$_POST['mdp']);

	if($existe!=NULL)
	{
		

		echo '[{"message":True,"nom": '.$existe['nom'].', "prenom":'.$existe['prenom'].'}]';
		
	}
	else
	{
		echo '[{"message":False}]';
	}

?>