# ==========================================
# Stage 1 - Build the application (Java 21)
# ==========================================
FROM maven:3.9.9-eclipse-temurin-21-jammy AS build

WORKDIR /app

# Copy pom.xml first
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy project source
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# ==========================================
# Stage 2 - Run the application (Java 21)
# ==========================================
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy generated jar safely using wildcards
COPY --from=build /app/target/*.jar app.jar

# Synchronized Spring Boot Port from application properties
EXPOSE 8083

# Start Application
ENTRYPOINT ["java","-jar","app.jar"]