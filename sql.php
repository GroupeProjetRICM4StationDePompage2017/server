<?php


class SQL{
//===============================================================//
//==========================CONNEXION============================//
//===============================================================//

	private $bdd;

	public function __construct(PDO $connector) { $this->bdd = $connector; }

	/*Fonction qui permet de savoir si l'utilisateur est dans la base de donnée
	return TRUE : utilisateur présent et bon mot de passe
	return FALSE : utilisateur non dans la base ou mat de passe faux */
	public function identification($log,$mdp)//ok
	{
		$isjardinier = $this->identificationJardinier($log,$mdp);
		if($isjardinier==NULL)
		{
			$isadherent = $this->identificationAdherents($log,$mdp);
			if($isadherent==NULL)
			{return NULL;}
			return $isadherent;
		}
		return $isjardinier;
	}

	public function identificationJardinier($log,$mdp)
	{
		$mdpC = md5($mdp);
		$reponse = $this->bdd->query('SELECT * FROM jardinier WHERE login =\''. $log.'\' and pwd = \''.$mdpC.'\'');
		$donnees = $reponse->fetch(PDO::FETCH_ASSOC);
		$reponse->closeCursor();
		if(!($donnees))
		{
			return NULL;
		}
		$donnees["isGardener"] = "True";
		return json_encode($donnees);
	}

	public function identificationAdherents($log,$mdp)
	{
		$mdpC = md5($mdp);
		$reponse = $this->bdd->query('SELECT * FROM adherents WHERE login =\''. $log.'\' and pwd = \''.$mdpC.'\'');
		$donnees = $reponse->fetch(PDO::FETCH_ASSOC);
		$reponse->closeCursor();

		//L'utilisateur n'est pas dans la base de donné jardinier ou son motde passe est faux
		if(!($donnees))
		{
			return NULL;
		}
		$donnees["isGardener"] = "False";
		return json_encode($donnees);
	}


	//===============================================================//
	//==========================REQUETE==============================//
	//===============================================================//
	/*Obtenir la dernière valeur de la pompe*/
	public function getLastData()
	{
		$statement = $this->bdd->prepare('SELECT * FROM cuvemeasure ORDER BY date DESC, time DESC');
		$statement->execute();
		$donnees = $statement->fetch(PDO::FETCH_ASSOC);
	    return json_encode($donnees);

	}

	/*Obtenir les valeurs correspondant au mois $month*/
	public function getMonthData($month,$year)
	{
		$indice = 0;
		$tableaux = array();
		$reponse = $this->bdd->query('SELECT * FROM cuvemeasure WHERE MONTH(date) = \''.$month.'\' and YEAR(date) = \''.$year.'\' ORDER BY date,time');
		while ($donnees = $reponse->fetch(PDO::FETCH_ASSOC))
		{
			$tableaux[$indice] = $donnees;
			$indice++;
		}
		$reponse->closeCursor();
		return json_encode($tableaux);
	}

	/*Obtenir les 24 dernière valeur qui correspond au normalement à 24h*/
	public function getLastHourData($nbvalue)
	{
		$indice = 0;
		$reponse = $this->bdd->query('SELECT * FROM cuvemeasure ORDER BY date DESC, time DESC');
		$tableaux = array();
		while (($donnees = $reponse->fetch(PDO::FETCH_ASSOC))&&$indice<$nbvalue)
		{
			$tableaux[$indice] = $donnees;
			$indice++;
		}
		$reponse->closeCursor();
		return json_encode($tableaux);
	}

	/**/
	public function getOrder()
	{
		$indice = 0;
		$reponse = $this->bdd->query('SELECT * FROM ordres WHERE is_executed=0 ORDER BY date DESC, time DESC;');
		$donnees = $reponse->fetch(PDO::FETCH_ASSOC);
		$reponse->closeCursor();
		if(!($donnees))
		{
			return NULL;
		}
		return json_encode($donnees);
	}

	//===============================================================//
	//===========================INSERT==============================//
	//===============================================================//

	/*Ajoute un user dans la table jardinier si le paramètre $jar = True et dans la table adhérent sinon*/
	function addUser($log,$mdp,$nom,$pre,$jar)//ok
	{
		$res = false;
		//Vérification que l'utilisateur n'existe pas
		if($this->identification($log,$mdp)!=NULL)
		{return $res;}

		$mdpC = md5($mdp);
		$arrayUser= array('login'=>$log,'mdp'=>$mdpC,'nom'=>$nom,'pre'=>$pre);
		if(strcmp($jar,'True')==0)
		{
			$req = $this->bdd->prepare('INSERT INTO jardinier VALUES (:login,:mdp,:nom,:pre)');
			$res = $req->execute($arrayUser);
			
		}
		else
		{
			$req = $this->bdd->prepare('INSERT INTO adherents VALUES (:login,:mdp,:nom,:pre)');
			$res = $req->execute($arrayUser);	
		}
		return $res;
	}

	/*Ajoute une données dans la table cuvemeasure*/
	function addData($id,$date,$time,$level,$state,$levelb)
	{
		$res = false;
		if($level<9 and $level>=0 and $levelb<11 and $levelb>=0)//le level est entre 0 et 8 et le level de la batere est entre 0 et 10
		{
			$arrayData= array('id'=>$id,'date'=>$date,'time'=>$time,'level'=>$level,'state'=>$state,'levelb'=>$levelb);

			$req = $this->bdd->prepare('INSERT INTO cuvemeasure VALUES (:id,:date,:time,:level,:state,:levelb)');
			$res = $req->execute($arrayData);
		}
		return $res;
	}

	/*Ajoute une données dans la table cuvemeasure*/
	function addOrder($id,$login,$mdp,$levelrequire)
	{
		$res = false;

		if($this->identificationJardinier($login,$mdp)==NULL)
		{	return $res;}

		if($levelrequire<9 and $levelrequire>=0)//le level est entre 0 et 8 et le level de la batere est entre 0 et 10
		{
			$arrayData= array('id'=>$id,'date'=>date("Y-m-d"),'time'=>date("H:i:s"),'levelr'=>$levelrequire);
			$req = $this->bdd->prepare('INSERT INTO ordres(idDevice,date,time,level_require,is_executed) VALUES (:id,:date,:time,:levelr,0)');
			$res = $req->execute($arrayData);	
		}
		return $res;
	}

	function updateOrder($id,$login,$mdp,$levelrequire)
	{
		$res = false;

		if($this->identificationJardinier($login,$mdp)==NULL)
		{	return $res;}

		if($levelrequire<9 and $levelrequire>=0)//le level est entre 0 et 8 et le level de la batere est entre 0 et 10
		{
			$arrayData= array('id'=>$id,'levelr'=>$levelrequire);
			$req = $this->bdd->prepare('UPDATE ordres SET idDevice =:id, level_require=:levelr WHERE is_executed=0 ;');
			$res = $req->execute($arrayData);	
		}
		return $res;
	}
}
?>

