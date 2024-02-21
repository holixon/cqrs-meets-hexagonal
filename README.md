# cqrs-meets-hexagonal
This example project is intended as a proof of concept that CQRS/ES can be implemented in a lightweight way without using a dedicated CQRS framework. We will just be using Spring-Boot and some other libraries.

## Nasa Api Doc

* https://api.nasa.gov
* https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf

## Run Flyway Migrations locally

Because of mixed Java and SQL migration, we need first to clean and compile Java classes to make it work.

```shell
$ cd demo-command
$ mvn clean compile flyway:migrate -Dflyway.configFiles=../flyway/local.conf
$ mvn clean compile flyway:repair -Dflyway.configFiles=../flyway/local.conf
```
