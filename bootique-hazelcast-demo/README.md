# bootique-hazelcast-demo

An example of [Hazelcast JCache](http://docs.hazelcast.org/docs/3.4/manual/html/jcache.html) usage in [Bootique](http://bootique.io) app.
    
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
java -jar bootique-hazelcast-demo/target/bootique-hazelcast-demo-1.0-SNAPSHOT.jar
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
Such kind of cache configuration should be contributed into Bootique via extender, see 
[Application.java](https://github.com/bootique-examples/bootique-jcache-demo/blob/master/bootique-hazelcast-demo/src/main/java/Application.java):
```java
JCacheModule.extend(binder).setConfiguration("myCache2", programmaticCache);
```
* **declaratively** using hazelcast.xml or hazelcast-client.xml. E.g. *hazelcast.xml* is used in the demo:
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
                        class-name="io.bootique.hazelcast.demo.MyCache1EntryCreatedListenerFactory"/>
            </cache-entry-listener>
        </cache-entry-listeners>
        <in-memory-format>OBJECT</in-memory-format>
    </cache>
    
</hazelcast>
```
Plug in *hazelcast.xml* into Bootique via *config.yml*: 
```yaml
jcache:
  configs:
    - "bootique-hazelcast-demo/hazelcast.xml"

```
Run the demo command:
```bash
java -jar bootique-hazelcast-demo/target/bootique-hazelcast-demo-1.0-SNAPSHOT.jar --config=bootique-hazelcast-demo/config.yml --demo
```
Look through the log:
```
...
INFO  [2017-09-27 12:17:51,164] hz.file:/Users/your_user/bootique-jcache-demo/bootique-hazelcast-demo/hazelcast.xml.event-1 i.b.h.d.MyCache1EntryCreatedListener: an entry k: 1, v: 1 is added to myCache1
INFO  [2017-09-27 12:17:51,166] hz.file:/Users/your_user/bootique-jcache-demo/bootique-hazelcast-demo/hazelcast.xml.event-1 i.b.h.d.MyCache2EntryListenerConfiguration: an entry k: key1, v: value1 is added to myCache2
```
