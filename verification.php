<?php

	require_once('sql.php');
	$existe = identification($_POST['login'],$_POST['mdp']);

	if($existe!=NULL)
	{
		

		echo '[{"message":True,"nom": '.$existe['nom'].', "prenom":'.$existe['prenom'].'}]';
		
	}
	else
	{
		echo '[{"message":False}]';
	}

?>