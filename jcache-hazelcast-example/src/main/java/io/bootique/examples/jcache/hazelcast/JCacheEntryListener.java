package io.bootique.examples.jcache.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

public class JCacheEntryListener implements CacheEntryCreatedListener<String, Integer> {

    static final Logger LOGGER = LoggerFactory.getLogger(JCacheEntryListener.class);

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends Integer>> iterable) throws CacheEntryListenerException {
        iterable.forEach(event -> LOGGER.info("JCache 'create' event...  Key: {}, new value: {}",
                event.getKey(),
                event.getValue()));
    }
}
