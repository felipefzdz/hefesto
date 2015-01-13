[![Build Status](https://travis-ci.org/olid16/hefesto.svg?branch=master)](https://travis-ci.org/olid16/hefesto)
[![Coverage Status](https://img.shields.io/coveralls/olid16/hefesto.svg)](https://coveralls.io/r/olid16/hefesto)

# HEFESTO
Hefesto is a Java app that models a small job website. [Hefesto](http://en.wikipedia.org/wiki/Hephaestus) is the Greek god of craftsmen, blacksmiths and another workers, so that's why I picked up his name.

The app spec can be found [here](https://github.com/TheLadders/object-calisthenics#exercise). The intent of this project is exercise some techniques or tools as TDD, DDD, microservices, NoSql or BDD.

I've been blogging about this project [here](olid16.github.io). The current project status is in an early stage, but as soon as I get some stable architecture, I would explain further the decisions behind it.

There is not any UI so far or any deployable, but If you want to run the app you will need to do this:

1. Download the code: git clone https://github.com/olid16/hefesto.git
2. [Install MongoDb](http://docs.mongodb.org/manual/installation/) and start a local instance (mongod).
3. [Install RabbitMQ](http://www.rabbitmq.com/) and start a local instance (rabbitmq-server)
4. Run mvn clean install. [Capsule](https://github.com/puniverse/capsule) will create a couple of fat jars. Run them:
    
    java -jar user-service/target/user-service-1.0-SNAPSHOT-capsule-fat.jar server user-service/userService.yml
    
    java -jar job-service/target/job-service-1.0-SNAPSHOT-capsule-fat.jar server job-service/jobService.yml
5. The servers will be listening at 8080 and 8081 ports. The future idea is to support [HATEOAS](http://en.wikipedia.org/wiki/HATEOAS) in order to provide a self discoverable API.
