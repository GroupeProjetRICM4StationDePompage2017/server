<?php

	require_once('sql.php');
	require_once('MaBD.php');

	$sql = new SQL(MaBD::getInstance());
	$data = $sql->getOrder();
	if($data!=NULL)
	{
		echo '[{"message":"True","value":'.$data.'}]';
		
	}
	else
	{
		echo '[{"message":"False", "erreur":"Il y n\'y a pas d\'ordre en cours"}]';
	}
?>