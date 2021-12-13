FROM eclipse-temurin:11-alpine
ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} app.jar

# SET UP TIMEZONE
ENV TZ="Europe/Budapest"

EXPOSE 8080

ENTRYPOINT ["java","-Dhttp.auth-token=<API_KEY>","-Dspring.datasource.url=jdbc:postgresql://<HOST>:5432/<DATABASE>?user=<USER>&password=<PASSWORD>&ApplicationName=boat_api_dev","-jar","/app.jar"]