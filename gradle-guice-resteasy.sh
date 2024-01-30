# takes in a name of dir to create from cmd line arg
dir_name=$@

# creates and moves to the dir
mkdir $dir_name
cd $dir_name

# create settings.gradle file
echo "rootProject.name = '${dir_name}'" >> settings.gradle

# create the build.gradle file
echo "plugins {
//    id 'application'
    id 'java'
    id 'eclipse'
    id 'eclipse-wtp'
    id 'war'
}

repositories {
     mavenCentral()
     mavenLocal()
}

dependencies {
        
    implementation 'org.hibernate:hibernate-validator:6.0.11.Final'
    implementation 'org.hibernate:hibernate-core:5.3.2.Final'
    implementation 'org.postgresql:postgresql:42.2.2'
    
    implementation 'com.google.inject:guice:4.2.0'
    implementation 'com.google.inject.extensions:guice-servlet:4.2.0'
    implementation 'com.google.inject.extensions:guice-persist:4.2.0'
    
    implementation 'org.jboss.resteasy:resteasy-jaxrs:3.6.0.Final'
    implementation 'org.jboss.resteasy:resteasy-guice:3.6.0.Final'    
    implementation 'org.jboss.resteasy:resteasy-jaxb-provider:3.6.0.Final'
    implementation 'org.jboss.resteasy:resteasy-html:3.6.0.Final'

    implementation 'javax.servlet:javax.servlet-api:3.1.0'
    implementation group: 'javax.servlet', name: 'jstl', version: '1.2'

}

// application {
//     mainClass = 'learn.$dir_name.main.Main'
// }" >> build.gradle

# create required dir structure
mkdir -p src/main/java/learn/$dir_name/main
mkdir -p src/main/resources/META-INF
mkdir -p src/main/webapp/WEB-INF

# optional dirs
mkdir -p src/main/java/learn/$dir_name/resource
mkdir -p src/main/java/learn/$dir_name/service
mkdir -p src/main/java/learn/$dir_name/model

# create Main.java file
echo "package learn.${dir_name}.main;

public class Main {
    public static void main(String[] args) {
        System.out.println(\"Hello, World!\");
    }
}" >> src/main/java/learn/$dir_name/main/Main.java

# create ResyEasyServices.java file
echo "package learn.$dir_name.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestEasyServices extends Application {

    private Set <Object> singletons = new HashSet <Object> ();

    public RestEasyServices() {
        Injector injector = Guice.createInjector(new JpaPersistModule("${dir_name}"));
//    	singletons.add(injector.getInstance(Test.class));
    }

    @Override
    public Set <Object> getSingletons() {
        return singletons;
    }
}" >> src/main/java/learn/$dir_name/service/RestEasyServices.java

# create web.xml file
echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>
<web-app>
    <filter>
        <filter-name>Resteasy</filter-name>
        <filter-class>
            org.jboss.resteasy.plugins.server.servlet.FilterDispatcher
        </filter-class>
        <init-param>
            <param-name>javax.ws.rs.Application</param-name>
            <param-value>learn.$dir_name.service.RestEasyServices</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>Resteasy</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>" >> src/main/webapp/WEB-INF/web.xml

# create the index.jsp file
echo "<html>
<head>
<meta charset=\"UTF-8\">
<title>Hello World</title>
</head>
<body>
<h1>Hello World</h1>
</body>
</html>" >> src/main/webapp/index.jsp

# create persistence.xml file
echo "<?xml version=/"1.0/" encoding=/"UTF-8/"?>

<persistence version=/"2.0/" xmlns=/"http://java.sun.com/xml/ns/persistence/"
   xmlns:xsi=/"http://www.w3.org/2001/XMLSchema-instance/" 
   xsi:schemaLocation=/"http://java.sun.com/xml/ns/persistence \
    http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd/">

    <persistence-unit name=/"${dir_name}/" transaction-type=/"RESOURCE_LOCAL/">
		
        <class>learn.${dir_name}.main.Main</class>
        <class>learn.${dir_name}.service.RestEasyServices</class>
     
        <properties>
            <property name=/"jakarta.persistence.jdbc.driver/" value=/"org.postgresql.Driver/"/>
            <property name=/"jakarta.persistence.jdbc.url/" value=/"jdbc:postgresql://localhost:5432/${dir_name}/"/>
            <property name=/"jakarta.persistence.jdbc.user/" value=/"axelor/"/>
            <property name=/"jakarta.persistence.jdbc.password/" value=/"axelor/"/>

            <!-- Hibernate properties -->
            <property name=/"hibernate.dialect/" value=/"org.hibernate.dialect.PostgreSQLDialect/"/>
            <property name=/"hibernate.show_sql/" value=/"true/"/>
            <property name=/"hibernate.hbm2ddl.auto/" value=/"update/"/> <!-- or create, validate, none -->

            <!-- C3P0 Connection Pool Configurations (Optional) -->
            <!--
            <property name=/"hibernate.c3p0.min_size/" value=/"5/"/>
            <property name=/"hibernate.c3p0.max_size/" value=/"20/"/>
            <property name=/"hibernate.c3p0.timeout/" value=/"300/"/>
            <property name=/"hibernate.c3p0.max_statements/" value=/"50/"/>
            <property name=/"hibernate.c3p0.idle_test_period/" value=/"3000/"/>
            -->

        </properties>
    </persistence-unit>
</persistence>" >> src/main/resources/META-INF/persistence.xml


# build it, forcing all dependencies to be checked and fetched (has some issues)
# gradle buildNeeded
# gradle build --rerun-tasks

# print done
echo "done!"