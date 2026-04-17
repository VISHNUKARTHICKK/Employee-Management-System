FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 81

CMD ["java", "-jar", "target/Employee-Management-System-0.0.1-SNAPSHOT.jar"]