# todo project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Prerequirement

1. Intellij
1. GraalVM (or docker and Java 11)
1. Docker

My suggestion is to use `SDKMAN` for Mac/Linux [https://sdkman.io/](https://sdkman.io/)

For whom is using Windows, maybe use WLS2 and linux is a better option, I will provide some script
but only for Mac/Linux.

The demo project require **mongodb**, and will be installed/running as docker container.
I provided few script for automatically download and configure the user.

### Configure mongodb

run `bin/startMongo.sh` the first execution will download the mongodb container and run it, creating
the application user (`cscamp`) 

# Install native image builder
Setup GraalVM as default vm in the path and run: 
```
gu install native-image
```

Check the prerequirement for your environment here [https://www.graalvm.org/reference-manual/native-image/](https://www.graalvm.org/reference-manual/native-image/)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `cs-camp-1.0.0-SNAPSHOT-runner.jar` file in the `/build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/cs-camp-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/cs-camp-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

# RESTEasy JAX-RS

Guide: https://quarkus.io/guides/rest-json


