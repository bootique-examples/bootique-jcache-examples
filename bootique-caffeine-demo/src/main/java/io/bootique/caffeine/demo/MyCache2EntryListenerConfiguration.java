package io.bootique.caffeine.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;

public class MyCache2EntryListenerConfiguration implements CacheEntryListenerConfiguration<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(MyCache2EntryListenerConfiguration.class);

    @Override
    public Factory<CacheEntryListener<? super String, ? super String>> getCacheEntryListenerFactory() {
        return (Factory<CacheEntryListener<? super String, ? super String>>) () ->
                (CacheEntryCreatedListener<String, String>) cacheEntryEvents -> cacheEntryEvents.forEach((event) -> {
                    logger.info("an entry k: " + event.getKey() + ", v: " + event.getValue() + " is added to myCache2");
                });
    }

    @Override
    public boolean isOldValueRequired() {
        return false;
    }

    @Override
    public Factory<CacheEntryEventFilter<? super String, ? super String>> getCacheEntryEventFilterFactory() {
        return null;
    }

    @Override
    public boolean isSynchronous() {
        return false;
    }
}
