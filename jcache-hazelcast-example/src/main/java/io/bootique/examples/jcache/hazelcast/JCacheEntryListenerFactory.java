package io.bootique.examples.jcache.hazelcast;

import javax.cache.configuration.Factory;

public class JCacheEntryListenerFactory implements Factory<JCacheEntryListener> {

    @Override
    public JCacheEntryListener create() {
        return new JCacheEntryListener();
    }
}
