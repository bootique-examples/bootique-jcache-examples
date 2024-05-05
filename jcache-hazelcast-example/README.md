# Bootique 3.x JCache with Caffeine Example

This is an example Bootique use of JCache with Hazelcast provider.

## Build and package

Run the following command to build the code, run the tests and package the app:
```
cd jcache-hazelcast-example/
mvn clean package
```

## Run

Check for available commands
```
java -jar target/jcache-hazelcast-example-3.0.jar
```

Run `-e` (`--explore-cache`) command. It will access the cache defined in the code, but also another cache defined in
`hazelcast.xml`, the Hazelcast default config file:

```
java -jar target/jcache-hazelcast-example-3.0.jar -e
```

