FROM openjdk:11-jdk-slim

ARG jarName
ADD "$jarName" '/app.jar'

EXPOSE 8080
CMD ["/bin/bash", "-c", "java -XX:MetaspaceSize=64M -Xms50M -Xmx50M -Dfile.encoding=UTF-8 \
-jar /app.jar"]

