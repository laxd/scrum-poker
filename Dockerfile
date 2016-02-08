FROM tomcat

COPY scrum-poker-web/target/*.war $CATALINA_HOME/webapps/poker.war
