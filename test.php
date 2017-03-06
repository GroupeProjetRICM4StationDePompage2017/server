<!DOCTYPE html>

<html>

  <head>
    <?php require_once('sql.php');
    require_once('MaBD.php');

    function genereValeurMesure()
    {
      //INSERT INTO `cuvemeasure` (`idDevice`, `date`, `time`, `level`, `state`, `levelbaterie`) VALUES ('1', '2017-02-27', '03:00:00', '5', '0', '10');

      

      for ($i = 0; $i <= 20; $i++) {
        //echo '1 - '.date("Y-m-d", rand( strtotime("Jan 01 2016"), strtotime("Dec 31 2016"))).' - '.(rand(0,23).':'.rand(0,59).':'.rand(0,59)).' - '.rand(0,8).' - '.rand(0,1).' - '.rand(1,10).'<br>';
        addData(1,date("Y-m-d", rand( strtotime("Jan 01 2016"), strtotime("Dec 31 2016"))),(rand(0,23).':'.rand(0,59).':'.rand(0,59)),rand(0,8),rand(0,1),rand(1,10));
        //$time = rand( strtotime("Jan 01 2007"), strtotime("Dec 31 2007") );
        //echo date("m-d-Y", $time);
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

     //$sh4 = 'login=ranma1_2&mdp=akane&nom=panda&prenom=ranma&jar=False';
     //$sh4 = 'login=la_scolopendre&mdp=le_cache_oeil&nom=Kaneki&prenom=Ken&jar=True';
     /* $sh4 = 'login=le_dieu_du_bonheur&mdp=hiyori&nom=Kami&prenom=Yato&jar=False';
 
      $curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/projet/server/inscription.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);

      echo $sh4Code;
*/
      /*$sh4 = 'id=1&date=2017-02-28&time=19:20:00&level=8&state=0&levelb=10';
      $curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/projet/server/ajouter_valeur.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);
      echo $sh4Code;*/

      $sh4 = 'login=la_scolopendre&mdp=le_cache_oeil&levelrequire=5';
      $curl = curl_init();
      curl_setopt($curl, CURLOPT_URL, "http://localhost/projet/server/ordres.php"); //Page sur laquelle envoyer les POST autrement dit la page vers laquelle pointe le formulaire
      curl_setopt($curl, CURLOPT_POST, 1);
      curl_setopt($curl, CURLOPT_POSTFIELDS, $sh4); //On envoie les valeurs
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
      $sh4Code = curl_exec($curl); 
      curl_close($curl);
      echo $sh4Code;

    ?>

    <!--<form method="post" action="verification.php">
    <p>Votre Login : <input type="text" name="login" /></p>
    <p>Votre mot de passe : <input type="password" name="mdp" /></p>
    <p><input type="submit" value="OK"></p>
    </form>-->
    


  </body>

</html>