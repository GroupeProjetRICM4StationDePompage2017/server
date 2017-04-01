<?php

	require_once('sql.php');
	require_once('MaBD.php');

	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');

	if(isset($_GET["id"]))
	{
		$sql = new SQL(MaBD::getInstance());
		$data = $sql->getOrder($_GET["id"]);
		if($data!=NULL)
		{
			echo '[{"message":"True","value":'.$data.'}]';
			
		}
		else
		{
			echo '[{"message":"False", "erreur":"Il y n\'y a pas d\'ordre en cours pour la device '.$_GET["id"].'"}]';
		}
	}
	else
	{
		$sql = new SQL(MaBD::getInstance());
		$data = $sql->getOrder(36);
		if($data!=NULL)
		{
			echo '[{"message":"True","value":'.$data.'}]';
			
		}
		else
		{
			echo '[{"message":"False", "erreur":"Il y n\'y a pas d\'ordre en cours pour la device 1"}]';
		}
	}
?>
