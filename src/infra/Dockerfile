FROM eclipse-temurin:25-jdk-alpine AS stage1

WORKDIR /work
ADD pom.xml mvnw ./
ADD .mvn .mvn/
ADD src src/

RUN ./mvnw -B package -DskipTests

FROM eclipse-temurin:25-jre-alpine AS stage2

WORKDIR /app
COPY --from=stage1 /work/target/restapi-0.0.1-SNAPSHOT.jar .
ENTRYPOINT exec java -jar restapi-0.0.1-SNAPSHOT.jar
