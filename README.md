[![Build Status](https://travis-ci.org/bootique-examples/bootique-jcache-demo.svg)](https://travis-ci.org/bootique-examples/bootique-jcache-demo)
# bootique-jcache-demo

An example of [JCache](https://www.jcp.org/en/jsr/detail?id=107) integrated into [Bootique](http://bootique.io).

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
java -jar target/bootique.jcache.demo-1.0-SNAPSHOT.jar
```

```
OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -d, --demo
           Simple command showing how to use JCache integration in an app.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.
```

`bootique-jcache` does not bundle a JCache provider. 
[Hazelcast JCache](http://docs.hazelcast.org/docs/3.4/manual/html/jcache.html) is explicitly included into app.

Hazelcast JCache provides two different ways of cache configuration:
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
* **declaratively** using hazelcast.xml or hazelcast-client.xml, e.g. *hazelcast.xml*:
```xml
<hazelcast xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.hazelcast.com/schema/config
                               http://www.hazelcast.com/schema/config/hazelcast-config-3.4.xsd"
           xmlns="http://www.hazelcast.com/schema/config">

    <cache name="myCache1">
        <key-type class-name="java.lang.String"/>
        <value-type class-name="java.lang.Integer"/>
        <statistics-enabled>true</statistics-enabled>
        <!--<management-enabled>true</management-enabled>-->
        <read-through>true</read-through>
        <write-through>true</write-through>
        <cache-entry-listeners>
            <cache-entry-listener old-value-required="true" synchronous="true">
                <cache-entry-listener-factory
                        class-name="io.bootique.jcache.demo.MyCache1EntryCreatedListenerFactory"/>
            </cache-entry-listener>
        </cache-entry-listeners>
        <in-memory-format>OBJECT</in-memory-format>
    </cache>

</hazelcast>
```

Run the demo command:
```bash
java -jar target/bootique.jcache.demo-1.0-SNAPSHOT.jar --config=config.yml --demo
```
Look through the log:
```
...
an entry k: 1, v: 1 is added to myCache1.
an entry k: key1, v: value1 is added to myCache2.
```
