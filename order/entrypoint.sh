#!/bin/bash

java -XX:MetaspaceSize=256M -Xms500M -Xmx500M -Dfile.encoding=UTF-8 \
-Djava.security.egd=file:/dev/./urandom \
-Duser.timezone=Asia/Shanghai \
-jar /app.jar
