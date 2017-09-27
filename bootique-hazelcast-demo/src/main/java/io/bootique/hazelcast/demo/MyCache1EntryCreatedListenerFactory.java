package io.bootique.hazelcast.demo;

import javax.cache.configuration.Factory;

public class MyCache1EntryCreatedListenerFactory implements Factory<MyCache1EntryCreatedListener> {

    @Override
    public MyCache1EntryCreatedListener create() {
        return new MyCache1EntryCreatedListener();
    }
}
