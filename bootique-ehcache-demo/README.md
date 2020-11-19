# bootique-ehcache-demo

An example of [Ehcache](http://www.ehcache.org) usage in [Bootique](http://bootique.io) app.
    
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
java -jar bootique-ehcache-demo/target/bootique-ehcache-demo-2.0.jar
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

* **Programmatic configuration**:
```java
Configuration<Long, Long> myCache2 = new MutableConfiguration<Long, Long>()
        .setTypes(Long.class, Long.class)
        .setExpiryPolicyFactory(_100ms)
        .addCacheEntryListenerConfiguration(new MyCache2EntryListenerConfiguration());
```
Such kind of cache configuration should be contributed into Bootique via extender, see 
[Application.java](https://github.com/bootique-examples/bootique-jcache-demo/blob/master/bootique-ehcache-demo/src/main/java/Application.java):
```java
JCacheModule.extend(binder).setConfiguration("myCache2", myCache2);
```
* **XML configuration**:
```xml
<config xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
        xmlns='http://www.ehcache.org/v3'
        xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core-3.0.xsd">

    <cache alias="myCache1">
        <key-type>java.lang.String</key-type>
        <value-type>java.lang.Integer</value-type>
        <expiry>
            <ttl unit="millis">100</ttl>
        </expiry>
        <listeners>
            <listener>
                <class>io.bootique.ehcache.demo.MyCache1EventListener</class>
                <event-firing-mode>SYNCHRONOUS</event-firing-mode>
                <event-ordering-mode>UNORDERED</event-ordering-mode>
                <events-to-fire-on>CREATED</events-to-fire-on>
            </listener>
        </listeners>
        <heap>10</heap>
    </cache>
</config>
```
Plug in *ehcahe.xml* into Bootique via *config.yml*: 
```yaml
jcache:
  configs:
    - "bootique-ehcache-demo/ehcache.xml"
```
Run the demo command:
```bash
java -jar bootique-ehcache-demo/target/bootique-ehcache-demo-2.0.jar --config=bootique-ehcache-demo/config.yml --demo
```
Look through the log:
```
...
INFO  [2017-09-27 12:12:02,266] Ehcache [null]-0 i.b.e.d.MyCache1EventListener: Event: CREATED Key: 1 old value: null new value: 1
INFO  [2017-09-27 12:12:02,269] Ehcache [null]-0 i.b.e.d.MyCache2EntryListenerConfiguration: an entry k: 1, v: 1 is added to myCache2
```
