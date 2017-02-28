<?php

	require_once('sql.php');

	if(isset($_POST["id"]) and isset($_POST["date"]) and isset($_POST["time"]) and isset($_POST["level"]) and isset($_POST["state"]) and isset($_POST["levelb"]))
	{
		$res = addData($_POST["id"],$_POST["date"],$_POST["time"],$_POST["level"],$_POST["state"],$_POST["levelb"]);
		if($res)
		{
			echo '[{"message":True}]';
		}
		else{echo '[{"message":False}]';}
	}
	else
	{
		echo '[{"message":False}]';
	}
	

?>