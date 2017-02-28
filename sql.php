<?php

//===============================================================//
//==========================CONNEXION============================//
//===============================================================//

/*Permet de se connecter à la base de données*/
function connectBase()
{
	try
	{
		return new PDO('mysql:host=localhost;dbname=pompe_connectee;charset=utf8', 'root', '');
	}
	catch (Exception $e)
	{
	        die('Erreur : ' . $e->getMessage());
	}
}

/*Fonction qui permet de savoir si l'utilisateur est dans la base de donnée
return TRUE : utilisateur présent et bon mot de passe
return FALSE : utilisateur non dans la base ou mat de passe faux */
function identification($log,$mdp)//ok
{
	$bdd = connectBase();
	$mdpC = md5($mdp);
	$reponse = $bdd->query('SELECT * FROM jardinier WHERE login =\''. $log.'\' and pwd = \''.$mdpC.'\'');
	$donnees;
	//L'utilisateur n'est pas dans la base de donné jardinier ou son motde passe est faux
	if(!($donnees = $reponse->fetch()))
	{
		$reponse->closeCursor();

		$reponse = $bdd->query('SELECT * FROM adherents WHERE login =\''. $log.'\' and pwd = \''.$mdpC.'\'');

		//L'utilisateur n'est pas dans la base de donné adherents ou son motde passe est faux
		if(!($donnees = $reponse->fetch()))
		{return NULL;}
		
		return $donnees;
		$reponse->closeCursor();
	}
	return $donnees;
}

//===============================================================//
//==========================REQUETE==============================//
//===============================================================//
/*Obtenir la dernière valeur de la pompe*/
function getLastData()
{
	$bdd = connectBase();
	$statement = $bdd->prepare('SELECT * FROM cuvemeasure ORDER BY date DESC, time DESC');
	$statement->execute();
	$donnees = $statement->fetch(PDO::FETCH_ASSOC);
    return json_encode($donnees);

}

/*Obtenir les valeurs correspondant au mois $month*/
function getMonthData($month,$year)
{
	$bdd = connectBase();
	$indice = 0;
	$tableaux = array();
	$reponse = $bdd->query('SELECT * FROM cuvemeasure WHERE MONTH(date) = \''.$month.'\' and YEAR(date) = \''.$year.'\' ORDER BY date,time');
	while ($donnees = $reponse->fetch(PDO::FETCH_ASSOC))
	{
		$tableaux[$indice] = $donnees;
		$indice++;
	}
	$reponse->closeCursor();
	return json_encode($tableaux);
}

/*Obtenir les 24 dernière valeur qui correspond au normalement à 24h*/
function getLastHourData($nbvalue)
{
	$bdd = connectBase();
	$indice = 0;
	$reponse = $bdd->query('SELECT * FROM cuvemeasure ORDER BY date DESC, time DESC');
	$tableaux = array();
	while (($donnees = $reponse->fetch(PDO::FETCH_ASSOC))&&$indice<$nbvalue)
	{
		$tableaux[$indice] = $donnees;
		$indice++;
	}
	$reponse->closeCursor();
	return json_encode($tableaux);
}

//===============================================================//
//===========================INSERT==============================//
//===============================================================//

/*Ajoute un user dans la table jardinier si le paramètre $jar = True et dans la table adhérent sinon*/
function addUser($log,$mdp,$nom,$pre,$jar)//ok
{
	$bdd = connectBase();
	$res = false;
	//Vérification que l'utilisateur n'existe pas
	if(identification($log,$mdp)!=null)
	{return $res;}

	$mdpC = md5($mdp);
	$arrayUser= array('login'=>$log,'mdp'=>$mdpC,'nom'=>$nom,'pre'=>$pre);
	if(strcmp($jar,'True')==0)
	{
		$req = $bdd->prepare('INSERT INTO jardinier VALUES (:login,:mdp,:nom,:pre)');
		$res = $req->execute($arrayUser);
		
	}
	else
	{
		$req = $bdd->prepare('INSERT INTO adherents VALUES (:login,:mdp,:nom,:pre)');
		$res = $req->execute($arrayUser);	
	}
	return $res;
}

/*Ajoute une données dans la table cuvemeasure*/
function addData($id,$date,$time,$level,$state,$levelb)
{
	$bdd = connectBase();
	$res = false;
	if($level<9 and $level>=0 and $levelb<11 and $levelb>=0)//le level est entre 0 et 8 et le level de la batere est entre 0 et 10
	{
		$arrayData= array('id'=>$id,'date'=>$date,'time'=>$time,'level'=>$level,'state'=>$state,'levelb'=>$levelb);

		$req = $bdd->prepare('INSERT INTO cuvemeasure VALUES (:id,:date,:time,:level,:state,:levelb)');
		$res = $req->execute($arrayData);
		
	}
	return $res;
}


//===============================================================//
//===========================AUTRE===============================//
//===============================================================//


//===============================================================//
//============================TEST===============================//
//===============================================================//


?>