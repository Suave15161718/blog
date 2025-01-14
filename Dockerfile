FROM openjdk:8

ADD /target/blog-1.0.0.jar blog-1.0.0.jar

ENV JAVA_OPTS="-Xmx8g -Xms4g -XX:MaxMetaspaceSize=2g -XX:MetaspaceSize=1g -XX:ParallelGCThreads=8"

EXPOSE 7091

ENTRYPOINT java -jar blog-1.0.0.jar