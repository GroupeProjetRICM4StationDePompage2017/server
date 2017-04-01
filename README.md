# ReadME ServeurCe dépôt github a été réalisé pour un projet de 4eme année en RICM à Polytech Grenoble.

Le programme est séparré en 2 dossiers:

* **something** : permet de communiquer avec une carte LoRa branché en série

* **something bis** : permet de communiquer avec la base de donnée via un set de fichier php.

Lien vers la page du projet :
http://air.imag.fr/index.php/Projets-2016-2017-Station_de_pompage_connect%C3%A9e

## Contexte



## Fonctionnement de l'API côté PHP

L'api retourne des fichiers sous format JSON

### Obtenir des données

**Lien :** http://stationpompeco2017.hopto.org/data.php
**Fonction :** GET
**Paramètre :**

* periode:
    * un mois en anglais(le paramètre year doit être set)
    * 24 : pour recuperer les 24 dernière heures
* year : une année(le paramètre période doit être set)
* id : l'id de la "device" à consulter, s'il n'est pas set la valeur par défaut est 36

**Retour  :** 

```htmlembedded
<!-- Une valeur de cuvemeasure -->
{
  "idDevice": "36",
  "date": "2017-04-01",
  "time": "10:42:46",
  "level": "8",
  "state": "0",
  "levelbaterie": "10"
}
```

**Exemple :**

| Lien | Resultat |
| ---- | -------- |
|http://stationpompeco2017.hopto.org/data.php| recupère la dernière mesure pour la devine numéro 36|
|http://stationpompeco2017.hopto.org/data.php?periode=march&year=2016|recupère les valeur du mois de mars 2016 pour la device 36|
|http://stationpompeco2017.hopto.org/data.php?id=1&periode=24|recupère les 24 dernière valeur pour la device 1|

### S’inscrire
**Lien :** http://stationpompeco2017.hopto.org/inscription.php
**Fonction :** POST
**Paramètre : Tous ces paramètre sont obligatoire** 

* login : login du nouvel utilisateur
* mdp : mot de passe du nouvel utilisateur
* nom : nom du nouvel utilisateur
* prenom : prénom du nouvel utilisateur
* jar : contient la string « True » si l’utilisateur est un jardinier(par mesure de sécurité se paramètre est toujours mis à False pour le moment)


**Retour  :** 
La page renvoie du json. S’il y a une erreur le champ message contient la string « False » et le champ erreur est disponible voir exemple vérification connexion. Sinon ce champ contient la string « True »


### Ajouter ordre
**Lien :** http://stationpompeco2017.hopto.org/ordres.php
**Fonction :** POST
**Paramètre : Tous ces champs sont obligatoire de plus seul un jardinier peut ajouter un ordre** 

* id : l'id de la device concerné
* login : le login de l’utilisateur qui souhaites ajouter un ordre
* mdp : mot de passe de l’utilisateur qui souhaites ajouter un ordre
* levelrequire : le niveau jusqu’où la cuve doit être remplie

**ATTENTION : s’il existe un ordre déjà un ordre non exécuté il est remplacé par le nouveau**

**Retour  :**
La page renvoie du json. S’il y a un problème lors de l'insertion de l’ordre le champ message contient la string « False » et un camp erreur est disponible. Sinon ce champ contient la string « True ».

### Obtenir ordre
**Lien :** http://stationpompeco2017.hopto.org/getOrdre.php
**Fonction :** GET
**Paramètre :** 

* id : l'id de la device concernée.S'il n'est pas présent l'id par défaut est 36

**Retour  :**
La page renvoie du json. S’il y n’y a pas d’ordre le champ message contient la string « False » et un camp erreur est disponible. Sinon ce champ contient la string « True » et le champ value contient les valeurs de l’ordre.

### Vérification connexion
**Lien :** http://stationpompeco2017.hopto.org/verification.php
**Fonction :** POST
**Paramètre :**
* login : le login de l’utilisateur à vérifier
* mdp : mot de passe de l’utilisateur à vérifier

**Retour  :**
La page renvoie du json. S’il y a un problème lors de la connexion l champ message contient la string « False » et un camp erreur est disponible. Sinon ce champ contient la string « True » et le champ value contient les valeurs de l’ordre.

### Obtenir la liste des devices
**Lien :** http://stationpompeco2017.hopto.org/device.php
**Fonction :** GET
**Paramètre :** aucun
**Retour  :**
la liste de l'ensemble des devices

### Mettre à jour un ordre
**Lien :** http://stationpompeco2017.hopto.org/updaeOrdre.php
**Fonction :** POST
**Paramètre :**
* id : l'id de la device concerné
* login : le login d'un jardinier
* mdp : mot de passe d'un jardinier

**Retour  :**
La page renvoie du json. S’il y a un problème lors de la connexion le champ message contient la string « False » et un camp erreur est disponible. Sinon ce champ contient la string « True » et le champ value contient les valeurs de l’ordre.

## Fonctionnement de l'API côté JAVA

L'api côté JAVA consiste en un programme java qui écoute un port COM. Quand le port reçoit des données le programme les lit et les enregistre dans la base de données. Ensuite il récupère dans la même base de donnée un ordre pour la "device" qui lui a envoyer les données et met à jour cet ordre.

La classe ThreadCommunicationJava qui correspond au programme principale peut communiquer avec la base de données par deux moyen :

* En exécutant directement des requêtes SQL(fonctionnalité uniquement disponible en local sur le serveur)
* En passant par l'API PHP. 
