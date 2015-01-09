[![Build Status](https://travis-ci.org/olid16/hefesto.svg?branch=master)](https://travis-ci.org/olid16/hefesto)
[![Coverage Status](https://img.shields.io/coveralls/olid16/hefesto.svg)](https://coveralls.io/r/olid16/hefesto)

# HEFESTO
Hefesto is a Java app that models a small job website. [Hefesto](http://en.wikipedia.org/wiki/Hephaestus) is the Greek god of craftsmen, blacksmiths and another workers, so that's why I picked up his name.

The app spec can be found [here](https://github.com/TheLadders/object-calisthenics#exercise). The intent of this project is exercise some techniques or tools as TDD, DDD, microservices, NoSql or BDD.

I've been blogging about this project [here](olid16.github.io). The current project status is in an early stage, but as soon as I get some stable architecture, I would explain further the decisions behind it.

There is not any UI so far or any deployable, but If you want to run the app you will need to do this:

1. Download the code: git clone https://github.com/olid16/hefesto.git
2. Import the project into Intellij or your favourite IDE (I didn't create any task in Maven to create a jar with an executable entry point, so for now, it would be easier to execute it from the IDE).
3. [Install MongoDb](http://docs.mongodb.org/manual/installation/) and start a local instance (mongod).
4. Run UserServiceLauncher and JobServiceLauncher classes. Both start a jetty server, through [Spark](http://sparkjava.com/), listening in 8080 the former and 8081 the latter.
5. You can access to different rest endpoints contained in Routes classes.
    
	`post("/user", (req, res) -> userController.create(req, res));`

	`get("/user/:userId", (req, res) -> userController.get(req, res));`
	
	`http://localhost:8080/user/54a1d864d4c6c21b47d8c267`
	
	The future idea is support [HATEOAS](http://en.wikipedia.org/wiki/HATEOAS) in order to provide a self discoverable API.