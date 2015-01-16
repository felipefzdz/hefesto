[![Build Status](https://travis-ci.org/olid16/hefesto.svg?branch=master)](https://travis-ci.org/olid16/hefesto)
[![Coverage Status](https://img.shields.io/coveralls/olid16/hefesto.svg)](https://coveralls.io/r/olid16/hefesto)

## HEFESTO
Hefesto is a Java app that models a small job website. [Hefesto](http://en.wikipedia.org/wiki/Hephaestus) is the Greek god of craftsmen, blacksmiths and another workers, so that's why I picked up his name.

The app spec can be found [here](https://github.com/TheLadders/object-calisthenics#exercise). The intent of this project is exercise some techniques or tools as TDD, DDD, Microservices, NoSql or BDD. I've been blogging about this project [here](olid16.github.io). 

### ARCHITECTURE

One of the biggest points of this project is to implement some microservices architecture, so I'll agree that is quite over-engineered. This is the basic structure for a couple of services (those will grow in the future, but the root idea will remain)

![image](http://i.imgur.com/EakPFHG.jpg)

Every service is written using Java 8 and Dropwizard. For now, every service (including DB and so on) will live in localhost, but the idea would be using some service discovery library to allow independent deploys and clustering.

Job Service needs to talk with User Service for authorization purposes, so I'm using Retrofit for Rest client. I'm wrapping that bit into Tenacity (Dropwizard integration with Hystrix), so I can provide a fallback just in case User Service is down or the call doesn't comply the SLA.

I've denormalized some of the data. Employer name lives into User collection and redundantly in Job Collection. Thanks to that I can avoid some network calls. In order to keep the consistency between stores, I'm using RabbitMQ to propagate Domain Events like Update User Name.

I'm using Maven as build system and Capsule as jar generator. I'm following BDD to design the domain and I'm using Cucumber JVM for that. Regarding Unit Testing I'm using JUnit, Mockito and Truth (assertion library from Google). Jacoco is the chosen coverage tool and [Coveralls](http://coveralls.io/r/olid16/hefesto) provides some nice Dashboard for that. It's connected with [Travis](https://travis-ci.org/olid16/hefesto) that serves as Continuous Integration guy.

My intent is keeping myself out of opinionated frameworks, so I could replace libraries easily. That's why I didn't use Play or Spring. For dependency injection I'm using Guice. Other libraries that I'm using from Google are Guava and Auto Value.

The best way to understand an API is playing with it. I'm using Swagger to create an interactive beautiful documentation. One of the project intents is using HATEOAS to provide a real RESTful api, but I've not implemented it yet.

I've followed some DDD advices in my code. If you have a look in the package structure you will see that every service has three high-level packages:

1. Actions. The 'procedural' guys that put in motion the domain. It's a good place to understand what the service does. They're written in the most business way possible so, like a User Story traslation. Acceptance testing starts here.

2. Domain. Where the business logic lives. It should be heavily OOP designed with packages like entities, values or events. The domain should be completely agnostic about who will consume it, so there shouldn't be any infrastructure leakage.

3. Infrastructure. Following the Hexagonal or Ports and Adapters architecture, we could see infrastructure as the delivery adapters and all the code needed by the service to run, e.g, dependency injection. This app has only three ports so far: http, mongodb and amqp.

The code is as expressive as possible. Clean code book ideas like small methods, single responsibility or clear naming are applied here. I've avoided primitives wrapping them into types making the code more suitable for early bug detection by compilers. And more understandable.

My general idea is creating code that puts the effort into writing and not reading. We spend more time reading than writing code, so it's our responsability as programmers creating the most readable and simple code that we can.

### INSTALLATION

1. Download the code: 

	git clone https://github.com/olid16/hefesto.git
2. [Install MongoDb](http://docs.mongodb.org/manual/installation/) and start a local instance (mongod).

3. [Install RabbitMQ](http://www.rabbitmq.com/) and start a local instance (rabbitmq-server)

4. Run [Breakerbox](https://github.com/yammer/breakerbox)

	cd breakerbox
	
	./runBreakerbox.sh
    
5. Run mvn clean install. [Capsule](https://github.com/puniverse/capsule) will create a couple of fat jars. Run them:
    
    java -jar user-service/target/user-service-1.0-SNAPSHOT-capsule-fat.jar server user-service/userService.yml
    
    java -jar job-service/target/job-service-1.0-SNAPSHOT-capsule-fat.jar server job-service/jobService.yml
    
6. The servers will be listening at 8081 and 8082 ports. You can browse the Swagger UI API here:

	http://localhost:8081/swagger
	
	http://localhost:8082/swagger