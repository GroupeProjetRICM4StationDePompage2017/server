<?php
require_once('sql.php');


$existe = identification($_POST['log'],$_POST['pass']);

if($existe!=NULL)
{
	$existe['prenom'];
	
	echo 'Bonjour '.$existe['nom'].' '.$existe['prenom'];
}
else
{
	echo 'Vous n\'appartenez pas à la base de données ou votre mot de passe ou login est faux';
}

?>