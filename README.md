[![Build Status](https://travis-ci.org/bootique-examples/bootique-jcache-examples.svg)](https://travis-ci.org/bootique-examples/bootique-examples-demo)
# bootique-jcache-demo

* [bootique-ehcache-demo](https://github.com/bootique-examples/bootique-jcache-demo/tree/master/bootique-ehcache-demo) -
an example of [Ehcache](http://www.ehcache.org) usage in [Bootique](http://bootique.io) app. 
* [bootique-hazelcast-demo](https://github.com/bootique-examples/bootique-jcache-demo/tree/master/bootique-hazelcast-demo) - 
an example of [Hazelcast JCache](http://docs.hazelcast.org/docs/3.4/manual/html/jcache.html) usage in [Bootique](http://bootique.io) app.

*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*

You can find different versions of framework in use at

* [3.x](https://github.com/bootique-examples/bootique-jcache-demo/tree/3.x)
* [2.x](https://github.com/bootique-examples/bootique-jcache-demo/tree/2.x)
* [1.x](https://github.com/bootique-examples/bootique-jcache-demo/tree/1.x)

   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo
      
Here is how to build it:
        
    git clone git@github.com:bootique-examples/bootique-jcache-demo.git
    cd bootique-jcache-demo
    mvn package
      
## Run the Demo

Run **bootique-ehcache-demo**:
```bash
java -jar bootique-ehcache-demo/target/bootique-ehcache-demo-1.0-SNAPSHOT.jar --config=bootique-ehcache-demo/config.yml --demo
```
Run **bootique-hazelcast-demo**:
```bash
java -jar bootique-hazelcast-demo/target/bootique-hazelcast-demo-1.0-SNAPSHOT.jar --config=bootique-hazelcast-demo/config.yml --demo
```
For additional details, please, take a peek at README.md of each of these examples.