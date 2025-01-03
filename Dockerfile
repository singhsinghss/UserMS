FROM openjdk:17-jdk-slim
RUN apt update && apt install -y curl
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build -x test
ARG JAR_FILE=build/libs/userms.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]