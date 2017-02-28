<?php

	require_once('sql.php');
	require_once('MaBD.php');

	function isMonth($month)
	{
		switch (strtolower($month)) {
		    case 'january':
		        return '01';
		        break;
		    case 'february':
		        return '02';
		        break;
		    case 'march':
		        return '03';
		        break;
		    case 'april':
		        return '04';
		        break;
		    case 'may':
		        return '05';
		        break;
		    case 'june':
		        return '06';
		        break;
		    case 'july':
		        return '07';
		        break;
		    case 'august':
		        return '08';
		        break;
		    case 'september':
		        return '09';
		        break;
		    case 'october':
		        return '10';
		        break;
		    case 'november':
		        return '11';
		        break;
		    case 'december':
		        return '12';
		        break;
		    default : 
		    	return '13';
		    	break;
		}
	}



	$sql = new SQL(MaBD::getInstance());
	if(isset($_GET["periode"])) //SOIT LES N DERNIERES VALEURS SOIT LES DONNEES D UN MOIS 
	{
		if($_GET["periode"]=="24")//on shouhaite les n dernière valeur
		{
			echo $sql->getLastHourData(24);
		}
		elseif (isset($_GET["year"]))//GET MONTH
		{
			$m = isMonth($_GET["periode"]);
			if($_GET["periode"]=="13")//erreur
			{
				echo "ERROR wrong get request";
			}
			else
			{
				echo  $sql->getMonthData($m,$_GET["year"]);
			}
		}
		else
		{
			echo "ERROR wrong get request";
		}
	}
	else
	{
		//renvoyer la dernière valeur
		echo $sql->getLastData();
	}


?>