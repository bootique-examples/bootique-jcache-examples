package io.bootique.caffeine.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

public class MyCache1EntryCreatedListener implements CacheEntryCreatedListener<String, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(MyCache1EntryCreatedListener.class);

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends Integer>> iterable) throws CacheEntryListenerException {
        iterable.forEach((event) -> {
            logger.info("an entry k: " + event.getKey() + ", v: " + event.getValue() + " is added to myCache1");
        });
    }
}
