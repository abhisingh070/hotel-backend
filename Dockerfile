# Stage 1: Build using Maven and Java 25
FROM maven:3.9.9-eclipse-temurin-25-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run using Java 25
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
# Note: Your pom.xml says <finalName>hotel_backend</finalName>
COPY --from=build /app/target/hotel_backend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]