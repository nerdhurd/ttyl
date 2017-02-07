package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.middleware.CsrfFilter;
import com.codahale.metrics.annotation.Timed;
import com.twilio.twiml.Dial;
import com.twilio.twiml.Number;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("v1/connectCall")
@Produces(MediaType.APPLICATION_XML)
@PermitAll
@CsrfFilter.CsrfFilterBypass
public class ConnectCallResource {

    private static final Logger logger = LoggerFactory.getLogger(ConnectCallResource.class);
    private final JedisPool pool;

    public ConnectCallResource(final JedisPool pool) {
        this.pool = pool;
    }

    @POST
    @Timed
    public TwiML connectCall(@NotEmpty @QueryParam("CallSid") final String callSid) throws TwiMLException {

        try (final Jedis redis = pool.getResource()) {
            final String to = redis.get(callSid);

            // Build our response
            final VoiceResponse voiceResponse = new VoiceResponse.Builder()
                    .say(
                            new Say.Builder("Connecting you to now…")
                                    .voice(Say.Voice.ALICE)
                                    .build()
                    )
                    .dial(
                            new Dial.Builder()
                                    .number(
                                            new Number.Builder(to)
                                                    .build()
                                    )
                                    .hangupOnStar(true)
                                    .build()
                    )
                    .build();

            // Return TwiML Response
            return voiceResponse;
        }

    }
}
