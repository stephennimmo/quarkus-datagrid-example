package com.redhat.energy;

import io.quarkus.infinispan.client.Remote;
import io.quarkus.runtime.StartupEvent;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.stream.Collectors;

@Path("/api/trades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TradeResource {

    RemoteCacheManager remoteCacheManager;

    @Inject
    @Remote("trade_cache")
    RemoteCache<Integer, Trade> tradeCache;

    @Inject TradeResource(RemoteCacheManager remoteCacheManager) {
        this.remoteCacheManager = remoteCacheManager;
    }

    void onStart(@Observes StartupEvent ev) {
        if (tradeCache.isEmpty()) {
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                tradeCache.put(i, new Trade(i, "CME", "CLK3", "BUY", BigDecimal.valueOf(1.0), BigDecimal.valueOf(75.00), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
            }
        }
    }

    @GET
    public Response get() {
        return Response.ok(tradeCache.values().stream().collect(Collectors.toList())).build();
    }

}
