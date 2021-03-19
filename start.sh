cd /home/fish/Git/Java/sw-backend;
redis-server;
git pull;
mvn package;
java -jar /home/fish/Git/Java/sw-backend/active4j-web/target/active4j-web-1.0.jar;