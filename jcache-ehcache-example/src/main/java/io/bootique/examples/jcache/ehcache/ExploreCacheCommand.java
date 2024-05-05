package io.bootique.examples.jcache.ehcache;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.inject.Inject;
import javax.inject.Provider;

public class ExploreCacheCommand extends CommandWithMetadata {

    static final Logger LOGGER = LoggerFactory.getLogger(ExploreCacheCommand.class);

    private final Provider<CacheManager> cacheManager;

    @Inject
    public ExploreCacheCommand(Provider<CacheManager> cacheManager) {
        super(CommandMetadata.builder(ExploreCacheCommand.class)
                .description("Demonstrates access to programmatic and configuration-based caches")
                .build());

        this.cacheManager = cacheManager;
    }

    @Override
    public CommandOutcome run(Cli cli) {

        // "cache1" is defined in the App module code and is always present
        Cache<Long, Long> cache1 = cacheManager.get().getCache("cache1", Long.class, Long.class);
        cache1.put(1L, 1000L);
        LOGGER.info("Value for 1 in 'cache1' is {}", cache1.get(1L));

        // "cache2" is defined in "ehcache.xml". It may or may not be present depending on the app
        // startup arguments
        Cache<String, Integer> cache2 = cacheManager.get().getCache("cache2", String.class, Integer.class);
        if (cache2 != null) {
            cache2.put("a", -100);
            LOGGER.info("Value for 'a' in 'cache2' is {}", cache2.get("a"));
        }

        return CommandOutcome.succeeded();
    }
}
