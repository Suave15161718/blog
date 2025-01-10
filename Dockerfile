FROM openjdk:8

ADD /target/blog-1.0.0.jar blog-1.0.0.jar

EXPOSE 7091

ENTRYPOINT java -jar blog-1.0.0.jar