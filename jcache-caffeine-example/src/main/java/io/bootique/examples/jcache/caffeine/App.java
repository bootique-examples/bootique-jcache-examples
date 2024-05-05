package io.bootique.examples.jcache.caffeine;

import io.bootique.BQModule;
import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.jcache.JCacheModule;

import javax.cache.configuration.Configuration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;
import java.util.concurrent.TimeUnit;

public class App implements BQModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(App.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {

        BQCoreModule.extend(binder).addCommand(ExploreCacheCommand.class);

        // create cache configuration in the code
        Factory<ExpiryPolicy> _100ms = CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MILLISECONDS, 100));
        Configuration<Long, Long> myCache2 = new MutableConfiguration<Long, Long>()
                .setTypes(Long.class, Long.class)
                .setExpiryPolicyFactory(_100ms)
                .addCacheEntryListenerConfiguration(new JCacheEntryListenerConfig());

        // add the Cache to Bootique
        JCacheModule.extend(binder).setConfiguration("cache1", myCache2);
    }
}
