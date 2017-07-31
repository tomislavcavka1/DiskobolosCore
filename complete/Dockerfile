FROM anapsix/alpine-java:jre8
MAINTAINER Tomislav Čavka "<tomislavcavka1@gmail.com>"
ADD target/DiskobolosCore-0.1.1.jar /DiskobolosCore-0.1.1.jar
EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/DiskobolosCore-0.1.1.jar"]d