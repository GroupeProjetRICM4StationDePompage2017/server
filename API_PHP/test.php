<!DOCTYPE html>

<html>

  <head>
    <?php require_once('sql.php');
    require_once('MaBD.php');


	//Fonction qui remplie la table cuvemeasure avec des valeurs aléatoire
    function genereValeurMesure()
    {
	  $sql = new SQL(MaBD::getInstance());
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Jan 01 2016"), strtotime("Jan 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Feb 01 2016"), strtotime("Feb 28 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Mar 01 2016"), strtotime("Mar 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Apr 01 2016"), strtotime("Apr 30 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("May 01 2016"), strtotime("May 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Jun 01 2016"), strtotime("Jun 30 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Jul 01 2016"), strtotime("jul 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Aug 01 2016"), strtotime("Aug 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Sep 01 2016"), strtotime("Sep 30 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Oct 01 2016"), strtotime("Oct 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Nov 01 2016"), strtotime("Nov 30 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
      for ($i = 0; $i <= 20; $i++) {
        $sql->addData(36,date("Y-m-d", rand( strtotime("Dec 01 2016"), strtotime("Dec 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
      }
    }

    ?>
    <title>Test</title>
  </head>

  <body>

    <?php 
      $nom = 'Kurosaki';
      $login = 'Shinigami_remplaçant';
      $mdp = 'bankai';
      $prenom = 'Ichigo';

	  //UTILISATEUR TEST
      //$sh4 = 'login=ranma1_2&mdp=akane&nom=panda&prenom=ranma&jar=False';
      //$sh4 = 'login=la_scolopendre&mdp=le_cache_oeil&nom=Kaneki&prenom=Ken&jar=True';
      //$sh4 = 'login=le_dieu_du_bonheur&mdp=hiyori&nom=Kami&prenom=Yato&jar=False';
      //$sh4 = 'login=sonGoku&mdp=kameha&nom=Son&prenom=Goku&jar=False';
      //$sh4 = 'login=Neha&mdp=mana&nom=Walker&prenom=Allen&jar=True';
      //$sh4 = 'login=lejardinier&mdp=pompe&nom=Fleur&prenom=Paul&jar=True';
      //$sh4 = 'login=kirua&mdp=gon&nom=Zoaldiek&prenom=Kirua&jar=True';
      //==============================================================================//
	  //Test Inscription
      //la chaine $sh4 contient l'ensemble des paramètre nécessaire à l'inscription(pour le format prendre exemple que les chaines ci dessus)
      //==============================================================================//
      /*$curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/server/inscription.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);
      echo $sh4Code;
      */
      //==============================================================================//
      //AJOUTE une valeur dans la table cuvemeasure (pas utile pour l'application web)
      //==============================================================================//
      /*$sh4 = 'id=1&date=2017-03-26&time=19:20:00&level=8&state=0&levelb=10';
      $curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/server/ajouter_valeur.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);
      echo $sh4Code;
*/

	  //==============================================================================//
	  //TEST ENREGISTRER UN ORDRE
	  //==============================================================================//
      /*$sh4 = 'login=la_scolopendre&mdp=le_cache_oeil&levelrequire=8&id=1';
      $curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/server/ordres.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);
      echo $sh4Code;*/
      
      /*$sh4 = 'login=la_scolopendre&mdp=le_cache_oeil&id=36';
      $curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/server/updateOrdre.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);
      echo $sh4Code;*/
	  
    ?>

    <!--
    //==============================================================================//
    //TEST VERIFICATION CONNEXION
    //==============================================================================//
    -->
    <!--<form method="post" action="verification.php">
    <p>Votre Login : <input type="text" name="login" /></p>
    <p>Votre mot de passe : <input type="password" name="mdp" /></p>
    <p><input type="submit" value="OK"></p>
    </form>-->

    <form method="post" action="verification.php">
    <p>Votre Login : <input type="text" name="login" /></p>
    <p>Votre mot de passe : <input type="password" name="mdp" /></p>
    <p><input type="submit" value="OK"></p>
    </form>
    
    <?php 
    $bdd = MaBD::getInstance();
    $tableaux = array();
    $indice = 0;
    $statement = $bdd->prepare('SELECT * FROM pompe_connectee.adherents;');
	$statement->execute();
    while ($donnees = $statement->fetch(PDO::FETCH_ASSOC))
	{
		$tableaux[$indice] = $donnees;
		$indice++;
	}
    $statement->closeCursor();
    echo json_encode($tableaux);
    
    
    genereValeurMesure();
    ?>

  </body>

</html>
