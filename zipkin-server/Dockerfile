FROM openjdk:8
VOLUME /temp
EXPOSE 9411
ADD ./zipkin-server-2.23.1-exec.jar zipkin-server.jar
ENTRYPOINT ["java","-jar", "zipkin-server.jar"]