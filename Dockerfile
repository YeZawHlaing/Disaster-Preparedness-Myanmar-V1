# -------- Build stage --------
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy Gradle files first (cache optimization)
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon || true

# Copy source and build Spring Boot fat JAR
COPY . /app
RUN ./gradlew clean bootJar -x test --no-daemon


# -------- Runtime stage --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
