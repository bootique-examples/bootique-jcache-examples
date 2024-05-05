# bootique-caffeine-demo

An example of Caffeine JCache usage in [Bootique](http://bootique.io) app.
    
*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo
      
Here is how to build it:
        
    git clone git@github.com:bootique-examples/bootique-jcache-demo.git
    cd bootique-jcache-demo
    mvn package 
      
## Run the Demo

Check the options available in your app:
```bash
java -jar bootique-caffeine-demo/target/bootique-caffeine-demo-1.0-SNAPSHOT.jar
```

```
OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -d, --demo
           Simple command showing how to use JCache in an app.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration
           options.
```

Caffeine JCache provides two different ways of cache configuration:
* **programmatically** using the Config API:
```java
Configuration<String, String> programmaticCache =
        new MutableConfiguration<String, String>()
                .setTypes(String.class, String.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
                .setReadThrough(true)
                .setWriteThrough(true)
                .addCacheEntryListenerConfiguration(new MyCache2EntryListenerConfiguration());

```
Such kind of cache configuration should be contributed into Bootique via extender, see 
[Application.java](https://github.com/bootique-examples/bootique-jcache-demo/blob/master/bootique-caffeine-demo/src/main/java/Application.java):
```java
JCacheModule.extend(binder).setConfiguration("myCache2", programmaticCache);
```
* **declaratively** using appliaction.conf placed in resources is used in the demo:
```hocon
caffeine.jcache {

  myCache1 {
    key-type = "java.lang.String"
    value-type = "java.lang.Integer"
    listeners = ["caffeine.jcache.listeners.test-listener"]
  }

  listeners {
    test-listener {
      class = "io.bootique.caffeine.demo.MyCache1EntryCreatedListener"
      filter = null
      synchronous = false
      old-value-required = false
    }
  }
}

```

Run the demo command:
```bash
java -jar bootique-caffeine-demo/target/bootique-caffeine-demo-1.0-SNAPSHOT.jar
```
Look through the log:
```
...
INFO  [2018-05-24 11:15:42,686] ForkJoinPool.commonPool-worker-1 i.b.c.d.MyCache1EntryCreatedListener: an entry k: 1, v: 1 is added to myCache1
INFO  [2018-05-24 11:15:42,686] ForkJoinPool.commonPool-worker-2 i.b.c.d.MyCache2EntryListenerConfiguration: an entry k: key1, v: value1 is added to myCache2
```
