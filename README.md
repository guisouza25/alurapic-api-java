## API - Alurapic

Esta RESTFul API foi desenvolvida utilizando o framework Quarkus, e é consumida por <a href = "https://github.com/guisouza25/alurapic">esta aplicação</a>, desenvolvida utilizando Angular.

## Executando a API no modo dev

Basta executar o comando `mvn compile quarkus:dev`, e a API estará disponível em `http://localhost:8080`

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `api-alurapic-java-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/api-alurapic-java-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/api-alurapic-java-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

# RESTEasy JAX-RS

<p>A Hello World RESTEasy resource</p>

Guide: https://quarkus.io/guides/rest-json
