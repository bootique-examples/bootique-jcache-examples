import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.di.BQModule;
import io.bootique.di.Binder;
import io.bootique.ehcache.demo.MyCache2EntryListenerConfiguration;
import io.bootique.jcache.JCacheModule;

import javax.cache.configuration.Configuration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;
import java.util.concurrent.TimeUnit;

public class Application implements BQModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .autoLoadModules()
                .module(Application.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {

        Factory<ExpiryPolicy> _100ms = CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MILLISECONDS, 100));
        Configuration<Long, Long> myCache2 = new MutableConfiguration<Long, Long>()
                .setTypes(Long.class, Long.class)
                .setExpiryPolicyFactory(_100ms)
                .addCacheEntryListenerConfiguration(new MyCache2EntryListenerConfiguration());

        //contribute the cache into BQ
        JCacheModule.extend(binder).setConfiguration("myCache2", myCache2);

        BQCoreModule.extend(binder).addCommand(DemoEhcacheCommand.class);
    }
}
