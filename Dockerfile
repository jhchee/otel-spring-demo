
# Use the official Gradle image to build the application
FROM gradle:8-jdk21 AS build
WORKDIR /home/gradle/project/
COPY . .
ENV JAVA_TOOL_OPTIONS="-XX:UseSVE=0"
RUN gradle build  --no-daemon

# Use the official OpenJDK image to run the application
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/project/application1/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:UseSVE=0", "-jar", "app.jar"]