# Use a Java base image for building the app
FROM openjdk:17-slim AS build

# Set the working directory for the build stage
WORKDIR /app

# Copy Gradle files and make gradlew executable
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle
RUN chmod +x gradlew

# Download dependencies and build the app without tests
RUN ./gradlew build -x test --no-daemon

# Copy the entire source code to the container and rebuild the app
COPY . /app
RUN ./gradlew build -x test --no-daemon

# Use a smaller JRE image to run the app
FROM openjdk:17-slim

# Set the working directory for the runtime image
WORKDIR /app

# Copy the final built JAR file from the build image
COPY --from=build /app/build/libs/0Auth-0.0.1-SNAPSHOT.jar /app/app.jar

# Install curl for debugging
RUN apt-get update && apt-get install -y curl

# Expose the port the application will run on
EXPOSE 4000

# Command to run the Spring Boot application
CMD ["java", "-jar", "/app/app.jar"]
