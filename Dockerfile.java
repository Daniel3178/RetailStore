FROM maven:latest AS builder
WORKDIR /app
COPY pom.xml .5 
COPY src ./src
RUN mvn clean package

FROM openjdk:latest
WORKDIR /app 
COPY --from=builder /app/target/RetailStore-1.0-SNAPSHOT.jar .  
CMD ["java", "-jar", "SoundGoodMusicDB-1.0-SNAPSHOT.jar"] 
