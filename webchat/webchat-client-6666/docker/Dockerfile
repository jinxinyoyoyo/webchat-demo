FROM openjdk:8-jre

MAINTAINER jinxin

ENV APP_VERSION 0.0.1-SNAPSHOT
#ENV DOCKERIZE_VERSION v0.6.0
#RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
#    && tar -C /usr/local/bin -zxvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
#    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app
WORKDIR /app
COPY webchat-client-6666-$APP_VERSION.jar app.jar
#ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "tcp://192.168.0.104:6666", "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
EXPOSE 6666
