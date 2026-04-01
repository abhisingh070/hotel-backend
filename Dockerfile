# Stage 1: Build using Java 25 and manually installed Maven
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Install Maven manually since standard images don't have Java 25 + Maven yet
RUN apk add --no-cache maven

# Copy your code
COPY . .

# Build the application
# Note: Using 'mvn' directly now that we installed it
RUN mvn clean package -DskipTests

# Stage 2: Run using Java 25
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app

# Match your <finalName> from pom.xml
COPY --from=build /app/target/hotel_backend.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]