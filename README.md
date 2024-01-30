#### CRUD operation using RESTEasy, Guice, JPA & gradle
----

This project is a demonstratino of integrating RESTEasy, Guice, JPA and Gradle to create a simple webapp with backend.

It uses PostgreSQL as database, JPAPersistenceModule provided by guice to interact with it.
Gradle for dependency management.
JSP with AJAX for front-end and RESTEasy for integrating front-end with back-end.

The entire folder structure was manually built, and can be build using the build script with name [`gradle-guice-resteasy.sh` ](./gradle-guice-resteasy.sh)

To generate the directory structure using script:
* First, make it executable using `chmod +x gradle-guice-resteasy.sh`.
* Use `. ./gradle-guice-resteasy.sh <dir_name>`, to make all dir structure and basic files.
* Create a database with `<dir_name>` or, change the [persistence.xml](./src/main/resources/META-INF/persistence.xml) file to use an existing one.