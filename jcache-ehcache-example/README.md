# Bootique 3.x JCache with EHCache Example

This is an example Bootique use of JCache with EHCache provider

## Build and package

Run the following command to build the code, run the tests and package the app:
```
cd jcache-ehcache-example/
mvn clean package
```

## Run

Check for available commands
```
java -jar target/jcache-ehcache-example-3.0.jar
```

Run `-e` (`--explore-cache`) command. It will access the cache defined in the code.
```
java -jar target/jcache-ehcache-example-3.0.jar -e
```

Run `-e` (`--explore-cache`) again with an additional EHCache config. It will access two caches - the one configured
in the code, and another one coming from `config.yml` and `ehcache.xml`:
```
java -jar target/jcache-ehcache-example-3.0.jar -e --config=config.yml
```