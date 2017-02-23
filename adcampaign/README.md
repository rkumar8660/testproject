# adcampaign
# Author: RajeshKumar
Spring Boot AdCampaign Web Application with embedded Tomcat Server, and package as an executable war file

Technologies used 
Spring Boot 1.5.1
Tomcat Embedded
Maven 3
Java 8
Rest Assured (spring.io.restassured) (Integration Testing)
H2 Database (Spring Boot inbuilt memory database)
JPARepository (Spring Repository for Persistent Mechanism)

Prerequisite:

1) Set Java8 and Maven in path


To run this project
```cmd
<Project root folder> mvn spring-boot:run
```

OR

if you are using STS (Spring tool suite - Eclipse based), Then Right Click and execute as Spring Boot Application


Post Example: (Test using postman app using chrome)
-----------------------------------------------------

http://localhost:6060/ad

{ "partner_id":"partner_2", "duration":30, "ad_content":"ad_2" };

GET Example
-----------

http://localhost:6060/ad/partner_2

All Ad campaign
---------------
http://localhost:6060/ad/list


H2 DB Console
-------------

http://localhost:6060/console/

JDBC URL: jdbc:h2:mem:AZ
USER NAME: sa
password: leave it empty








