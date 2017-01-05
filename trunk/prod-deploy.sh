mvn clean install -DskipTests
sudo rm -Rf /home/tomcat/survey-web-1.0-SNAPSHOT
sudo cp web/target/survey-web-1.0-SNAPSHOT.war /usr/share/apache-tomcat-7.0.56/webapps/amcr.war
