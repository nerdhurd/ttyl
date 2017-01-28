package co.willsalz.ttyl.resources.v1;

import com.codahale.metrics.annotation.Timed;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("v1/connectCall")
public class ConnectCallResource {

    @POST
    @Timed
    public TwiML connectCall(@Context HttpServletRequest req) throws TwiMLException {

        // Build our response
        final VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .say(
                        new Say.Builder("Thanks for calling TTYL!")
                                .voice(Say.Voice.ALICE)
                                .build()
                )
                .build();

        // Return TwiML Response
        return voiceResponse;
    }
}
