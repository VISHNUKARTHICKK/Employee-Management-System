FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 81git add Dockerfile

CMD ["java", "-jar", "target/Employee-Management-System-0.0.1-SNAPSHOT.jar"]