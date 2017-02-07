package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.StartCallRequest;
import co.willsalz.ttyl.service.CallService;
import com.codahale.metrics.annotation.Timed;
import com.twilio.rest.api.v2010.account.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;

@Path("v1/call")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StartCallResource {

    private static final Logger logger = LoggerFactory.getLogger(StartCallResource.class);
    private static final int TTL = Math.toIntExact(Duration.ofMinutes(5).getSeconds());
    private final CallService callService;
    private final JedisPool pool;

    public StartCallResource(final CallService callService, final JedisPool pool) {
        this.callService = callService;
        this.pool = pool;
    }

    @POST
    @Timed
    public Response startCall(@NotNull @Valid final StartCallRequest req) {

        final Call call = callService.makeCall(req.getFrom());

        try (final Jedis redis = pool.getResource()) {
            redis.setex(
                    // User's Phone Number
                    call.getSid(),

                    // How long we store the mapping
                    TTL,

                    // The representative's Phone Number
                    req.getTo()
            );
        }

        return Response.noContent().build();

    }

}
