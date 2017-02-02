package co.willsalz.ttyl.service;

import co.willsalz.ttyl.resources.v1.ConnectCallResource;
import com.google.common.annotations.VisibleForTesting;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;
import io.dropwizard.auth.basic.BasicCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Random;

public class PhoneService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);
    private final Random rng = new Random(System.nanoTime());

    private final TwilioRestClient client;
    private final BasicCredentials twilioCredentials;
    private final List<String> fromNumbers;
    private final URI baseUri;

    public PhoneService(final TwilioRestClient client,
                        final URI baseUri,
                        final BasicCredentials twilioCredentials,
                        final List<String> fromNumbers) {
        this.client = client;
        this.baseUri = baseUri;
        this.twilioCredentials = twilioCredentials;
        this.fromNumbers = fromNumbers;
    }

    public Call makeCall(final String to) {

        return makeCall(to, randomFrom());

    }

    @VisibleForTesting
    protected String randomFrom() {
        return this.fromNumbers.get(rng.nextInt(this.fromNumbers.size()));
    }

    public Call makeCall(final String to, final String from) {

        final PhoneNumber to_ = new PhoneNumber(to);
        final URI uri = UriBuilder.fromUri(baseUri)
//                .userInfo(twilioCredentials.getUsername() + ":" + twilioCredentials.getPassword())
                .path(ConnectCallResource.class)
                .build();
        return new CallCreator(to_, new PhoneNumber(from), uri).create(client);

    }

}
