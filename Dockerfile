# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files into the container
COPY build.gradle /app/build.gradle
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Download Gradle and dependencies
RUN ./gradlew

# Copy the entire project into the container
COPY . /app

# Build the bot
RUN ./gradlew build

# Command to run your bot
CMD ["java", "-jar", "build/libs/your-bot.jar"]
