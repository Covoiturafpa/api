# Projet Covoitur'Afpa

## Serveur Java et base de données (API et BDD)

Code du serveur Java et scripts PostGreSQL de la base de données pour l'application Covoitur'Afpa.

Covoitur'Afpa est une application dédiée au covoiturage allant et venant du centre Afpa de Rochefort (Charente-Maritime), utilisable par les stagiaires et les employés du centre.

L'application Covoitur'Afpa est un projet conçu par les stagiaires de la session 2022-2023 de la formation Concepteur Développeur d'Applications de l'Afpa de Rochefort, encadrée par le formateur Ludovic Esperce.

## Détails de l'API

L'API est composée d'une couche contrôleurs, d'une couche modèles et d'une couche DAO, ainsi que d'une couche de sécurité utilisant des JWT, de l'authentification d'utilisateur et des captchas.

Vous trouverez également des tests unitaires et d'intégration, organisés de façon à suivre l'architecture de l'API.

## Installation
Après avoir cloné le projet, créez une nouvelle base de données appelée 'CovoiturAfpa' dans votre client de base de données et sélectionnez-la comme base de données par défaut.
Puis lancez le scripts SQL contenu dans le fichier DBDeploymentScript.sql dans votre client.