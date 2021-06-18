FROM openjdk:11-jdk-slim

ARG jarName
ADD "$jarName" '/app.jar'

EXPOSE 8080
CMD ["/bin/bash", "-c", "java -XX:MetaspaceSize=256M -Xms200M -Xmx200M -Dfile.encoding=UTF-8 \
-jar /app.jar"]

