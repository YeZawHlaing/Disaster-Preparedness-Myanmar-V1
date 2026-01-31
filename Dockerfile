# -------- Build stage --------
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy Gradle files first for caching
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download dependencies (cache optimization)
RUN ./gradlew dependencies --no-daemon || true

# Copy source and build Spring Boot fat JAR
COPY . /app
RUN ./gradlew clean bootJar -x test --no-daemon

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built JAR
COPY --from=build /app/build/libs/*.jar app.jar

# Copy wait-for-it script
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh

# Expose port (make sure it matches your application properties)
EXPOSE 8080

# Wait for MySQL to be ready, then start Spring Boot
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--timeout=60", "--strict", "--", "java", "-jar", "app.jar"]
