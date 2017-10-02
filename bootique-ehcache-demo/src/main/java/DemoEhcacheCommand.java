import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;

import javax.cache.Cache;
import javax.cache.CacheManager;

public class DemoEhcacheCommand extends CommandWithMetadata {

    private Provider<CacheManager> cacheManager;

    @Inject
    public DemoEhcacheCommand(Provider<CacheManager> cacheManager) {
        super(CommandMetadata.builder(DemoEhcacheCommand.class)
                .name("demo")
                .shortName('d')
                .description("Simple command showing how to use JCache in an app.")
                .build());

        this.cacheManager = cacheManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {

        //retrieve the cache defined in ehcache.xml
        Cache<String, Integer> cache = cacheManager.get()
                .getCache("myCache1", String.class, Integer.class);

        //put an entry...
        //CacheEntryCreatedListener fires afterwards
        cache.put("1", 1);

        //retrieve the cache configured programmatically and contributed into Bootique
        Cache<Long, Long> myCache2 = cacheManager.get()
                .getCache("myCache2", Long.class, Long.class);

        //put an entry...
        //CacheEntryCreatedListener fires afterwards
        myCache2.put(1L, 1L);

        return CommandOutcome.succeeded();
    }
}
