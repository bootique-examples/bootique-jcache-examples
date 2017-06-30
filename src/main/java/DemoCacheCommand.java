import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;

import javax.cache.Cache;
import javax.cache.CacheManager;

public class DemoCacheCommand extends CommandWithMetadata {

    private Provider<CacheManager> cacheManager;

    @Inject
    public DemoCacheCommand(Provider<CacheManager> cacheManager) {
        super(CommandMetadata.builder(DemoCacheCommand.class)
                .name("demo")
                .shortName('d')
                .description("Simple command showing how to use JCache integration in an app.")
                .build());

        this.cacheManager = cacheManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {

        //retrieve the cache defined in the declaration file hazelcast.xml
        Cache<String, Integer> declarativeCache = cacheManager.get()
                .getCache("myCache1", String.class, Integer.class);

        //put an entry...
        //CacheEntryCreatedListener fires afterwards
        declarativeCache.put("1", 1);

        //retrieve the cache configured programmatically and contributed into Bootique
        Cache<String, String> programmaticCache = cacheManager.get()
                .getCache("myCache2", String.class, String.class);

        //put an entry...
        //CacheEntryCreatedListener fires afterwards
        programmaticCache.put("key1", "value1");

        return CommandOutcome.succeeded();
    }
}
