FROM openjdk:17-oracle
COPY target/used_vehicle-0.0.1.jar used_vehicle-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/used_vehicle-0.0.1.jar"]