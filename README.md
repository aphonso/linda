# linda
Linda Exercise

## Application configuration:
* Request Rate limit per second 80

## Kibana:
* http://[SERVER]:[PORT]/app/kibana

## Application endpoints:

* Displays simple information about how many market exchange rate were loaded
http://[SERVER]:[PORT]/lindacare

* Displays the endpoint descriptions and also gets you the CURL command to invoke them
http://[SERVER]:[PORT]/lindacare/swagger-ui.html

* Post (to add exchange rates, needs authentication username/password=admin/admin) and Get (to view all exchange rates)
http://[SERVER]:[PORT]/lindacare/api/v1/linda/market
	
Post example:
curl -u admin:admin -X POST -H "Content-Type: application/json" -d '{"userId":1,"currencyFrom":"EUR","currencyTo":"GBP","amountSell":1000.0,"amountBuy":747.1,"rate":0.7471,"timePlaced":"24-Jan-15 10:27:44","originatingCountry":"FR"}' http://[SERVER]:[PORT]/lindacare/api/v1/linda/market

## Some notes about the technologies and decisions taken for this project:
* Was developed with Java 8;
* Spring Framework (data-elasticsearch, jdbc, web, security);
* Uses Thymeleaf and bootstrap to display a simple page with the total amout of loaded market exchange rates;
* Uses a dedicated tomcat server;
* Uses a dedicated elastic search server;
* For the request rate limit it was used guava
* For describing the endpoints it was used springfox-swagger2;
* For the spring web security it was created user credentials in the application.properties file (spring.security.user.name) this decision was taking according to the very short time to make the exercise, it should instead be stored and encrypted in elastic search or database.

## How to build it?
* mvn clean package

## How to execute it locally?
mvn clean spring-boot:run