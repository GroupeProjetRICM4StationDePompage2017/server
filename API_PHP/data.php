<?php

	require_once('sql.php');
	require_once('MaBD.php');
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST');
	header('Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token');
	
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
	$resultat = false;
	$msg = "";
	if(isset($_GET["periode"])) //SOIT LES N DERNIERES VALEURS SOIT LES DONNEES D UN MOIS 
	{
		if($_GET["periode"]=="24")//on shouhaite les n dernière valeur
		{
			if(isset($_GET["id"])){$resultat = $sql->getLastHourData($_GET["id"],24);}
			else{$resultat = $sql->getLastHourData(36,24);}
			
			if($resultat==NULL){echo '[{"message":"False", "erreur":"Cette device n\'a pas de valeur)"}]';}
			else{echo $resultat;}
			
		}
		elseif (isset($_GET["year"]))//GET MONTH
		{
			$m = isMonth($_GET["periode"]);
			if($_GET["periode"]=="13")//erreur
			{
				echo '[{"message":"False", "erreur":"Problème requete(champ periode faux)"}]';
			}
			else
			{
				
				if(isset($_GET["id"])){$resultat = $sql->getMonthData($_GET["id"],$m,$_GET["year"]);}
				else{$resultat = $sql->getMonthData(36,$m,$_GET["year"]);}
				
				if($resultat==NULL){echo '[{"message":"False", "erreur":"Cette device n\'a pas de valeur)"}]';}
				else{echo $resultat;}
			}
		}
		else
		{
			echo '[{"message":"False", "erreur":"Problème requete(champ year faux)"}]';
		}
	}
	else
	{
		//renvoyer la dernière valeur
		if(isset($_GET["id"])){$resultat = $sql->getLastData($_GET["id"]);}
		else
		{$resultat = $sql->getLastData(36);}
		
		if($resultat==NULL){echo '[{"message":"False", "erreur":"Cette device n\'a pas de valeur)"}]';}
		else{echo $resultat;}
		
	}


?>
