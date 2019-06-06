#FROM openjdk:8-jdk-alpine
#ENV APP_HOME /opt
#
#ARG dburl
#ARG dbuser
#ARG dbpass
#ARG rabbithost
#
#RUN apk --no-cache add netcat-openbsd
#COPY target/USERS-0.0.1-SNAPSHOT.jar $APP_HOME
#COPY articles-entrypoint.sh $APP_HOME/articles-entrypoint.sh
#
#RUN echo "spring.datasource.url=$dburl">$APP_HOME/application.properties
#RUN echo "spring.datasource.username=$dbuser">>$APP_HOME/application.properties
#RUN echo "spring.datasource.password=$dbpass">>$APP_HOME/application.properties
#RUN echo "spring.rabbitmq.host=$rabbithost">>$APP_HOME/application.properties
#
#RUN chmod 775 $APP_HOME/articles-entrypoint.sh
#WORKDIR $APP_HOME
#ENTRYPOINT ["sh", "-c"]
#
#CMD [ "/opt/articles-entrypoint.sh" ]
#EXPOSE 8000

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]