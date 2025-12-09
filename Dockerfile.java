FROM maven:latest AS builder
WORKDIR /app
COPY pom.xml . 
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:17-jdk
WORKDIR /app 
COPY --from=builder /app/target/RetailStore-1.0-SNAPSHOT.jar .  
CMD ["java", "-jar", "RetailStore-1.0-SNAPSHOT.jar"] 
