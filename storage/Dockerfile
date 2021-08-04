FROM openjdk:11-jdk-slim

ARG jarName
ADD "$jarName" '/app.jar'

EXPOSE 8080
CMD ["/bin/bash", "-c", "java -XX:MetaspaceSize=100M -Xms100M -Xmx100M -Dfile.encoding=UTF-8 \
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar /app.jar"]

