package io.bootique.examples.jcache.caffeine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;

/**
 * Creates listeners for JCache CacheEntryEvents
 */
public class JCacheEntryListenerConfig implements CacheEntryListenerConfiguration<Long, Long> {

    static final Logger LOGGER = LoggerFactory.getLogger(JCacheEntryListenerConfig.class);

    @Override
    public Factory<CacheEntryListener<? super Long, ? super Long>> getCacheEntryListenerFactory() {
        return () -> (CacheEntryCreatedListener<Long, Long>) events -> events.forEach(event ->
                LOGGER.info("JCache 'create' event...  Key: {}, new value: {}",
                        event.getKey(),
                        event.getValue()));
    }

    @Override
    public boolean isOldValueRequired() {
        return false;
    }

    @Override
    public Factory<CacheEntryEventFilter<? super Long, ? super Long>> getCacheEntryEventFilterFactory() {
        return null;
    }

    @Override
    public boolean isSynchronous() {
        return false;
    }
}
