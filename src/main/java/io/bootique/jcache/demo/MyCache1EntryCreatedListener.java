package io.bootique.jcache.demo;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

public class MyCache1EntryCreatedListener implements CacheEntryCreatedListener<String, Integer> {

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends Integer>> iterable) throws CacheEntryListenerException {
        iterable.forEach((event) -> {
            System.out.println("an entry k: " + event.getKey() + ", v: " + event.getValue() + " is added to myCache1.");
        });
    }
}
