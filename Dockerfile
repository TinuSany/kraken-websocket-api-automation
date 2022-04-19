FROM maven:3.8.5-jdk-8
COPY src home/kraken/src
COPY target home/kraken/target
COPY pom.xml home/kraken/pom.xml
WORKDIR home/kraken
ENTRYPOINT mvn test verify
COPY home/kraken/target target