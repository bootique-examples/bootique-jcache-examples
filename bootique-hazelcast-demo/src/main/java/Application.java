import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.jcache.JCacheModule;
import io.bootique.hazelcast.demo.MyCache2EntryListenerConfiguration;

import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;

public class Application implements Module {

    public static void main(String[] args) {
        Bootique.app(args).autoLoadModules().module(Application.class).run();
    }

    @Override
    public void configure(Binder binder) {

        //programmatically configured cache
        Configuration<String, String> programmaticCache =
                new MutableConfiguration<String, String>()
                        .setTypes(String.class, String.class)
                        .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
                        .setReadThrough(true)
                        .setWriteThrough(true)
                        .addCacheEntryListenerConfiguration(new MyCache2EntryListenerConfiguration());

        //contribute the cache into BQ
        JCacheModule.extend(binder).setConfiguration("myCache2", programmaticCache);

        BQCoreModule.extend(binder).addCommand(DemoHazelcastCommand.class);
    }
}
