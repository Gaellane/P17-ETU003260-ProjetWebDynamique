#!/bin/bash

APP_NAME="ETU003260"
SRC_DIR="src/main/java/"
WEB_DIR="src/main/webapp"
BUILD_DIR="build"
TOMCAT_WEBAPPS="C:\apache-tomcat-10.1.28\webapps"
SERVLET_API_JAR="lib/*"

# Nettoyage du dossier build
rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/WEB-INF/classes

# Compilation des fichiers Java
find $SRC_DIR -name "*.java" > sources.txt
javac --release 17  -cp "lib/*" -d $BUILD_DIR/WEB-INF/classes @sources.txt || { echo "Compilation échouée"; exit 1; }
rm sources.txt

# Copie des fichiers web
cp -r $WEB_DIR/* $BUILD_DIR/

# Création du fichier WAR
cd $BUILD_DIR
jar -cvf ../$APP_NAME.war *
cd ..

# Copie du fichier WAR dans Tomcat
cp $APP_NAME.war "$TOMCAT_WEBAPPS/"

# Nettoyage temporaire
# rm -rf $BUILD_DIR

echo "Déploiement terminé. Redémarrez Tomcat."
