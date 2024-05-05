package io.bootique.examples.jcache.ehcache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An EhCache-specific listener of cache events. Declared in "ehcache.xml"
 */
public class EhcacheEventListener implements CacheEventListener<String, Integer> {

    static final Logger LOGGER = LoggerFactory.getLogger(EhcacheEventListener.class);

    @Override
    public void onEvent(CacheEvent<? extends String, ? extends Integer> event) {
        LOGGER.info("EhCache '{}' event...  Key: {}, new value: {}",
                event.getType(),
                event.getKey(),
                event.getNewValue());
    }
}
