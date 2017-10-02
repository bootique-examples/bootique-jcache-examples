package io.bootique.ehcache.demo;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCache1EventListener implements CacheEventListener<String, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(MyCache1EventListener.class);

    @Override
    public void onEvent(CacheEvent<String, Integer> cacheEvent) {
        logger.info("Event: " + cacheEvent.getType() + " Key: " + cacheEvent.getKey() + " old value: " + cacheEvent.getOldValue() + " new value: " + cacheEvent.getNewValue());
    }
}
