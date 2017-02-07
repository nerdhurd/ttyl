package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.TwilioCallRequest;
import co.willsalz.ttyl.middleware.CsrfFilter;
import com.codahale.metrics.annotation.Timed;
import com.twilio.twiml.TwiMLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v1/callEnded")
@Produces(MediaType.APPLICATION_XML)
@PermitAll
@CsrfFilter.CsrfFilterBypass
public class CallEndedResource {

    private static final Logger logger = LoggerFactory.getLogger(CallEndedResource.class);
    private final JedisPool pool;

    public CallEndedResource(final JedisPool pool) {
        this.pool = pool;
    }

    @POST
    @Timed
    public void callEnded(@NotNull @Valid final TwilioCallRequest req) throws TwiMLException {

        logger.info("Callended: {}", req);
        try (final Jedis redis = pool.getResource()) {
            redis.del(req.getCallSid());
        }

    }
}
