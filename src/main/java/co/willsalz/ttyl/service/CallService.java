package co.willsalz.ttyl.service;

import co.willsalz.ttyl.repositories.PhoneNumberRepository;
import co.willsalz.ttyl.resources.v1.ConnectCallResource;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;
import io.dropwizard.auth.basic.BasicCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class CallService {

    private static final Logger logger = LoggerFactory.getLogger(CallService.class);

    private final TwilioRestClient client;
    private final BasicCredentials twilioCredentials;
    private final URI baseUri;
    private final PhoneNumberRepository phoneNumbers;

    public CallService(final TwilioRestClient client,
                       final URI baseUri,
                       final BasicCredentials twilioCredentials,
                       final PhoneNumberRepository phoneNumbers) {
        this.client = client;
        this.baseUri = baseUri;
        this.twilioCredentials = twilioCredentials;
        this.phoneNumbers = phoneNumbers;
    }

    public Call makeCall(final String to) {

        return makeCall(new PhoneNumber(to), phoneNumbers.getPhoneNumber());

    }

    public Call makeCall(final PhoneNumber to, final PhoneNumber from) {

        final URI uri = UriBuilder.fromUri(baseUri)
                .userInfo(twilioCredentials.getUsername() + ":" + twilioCredentials.getPassword())
                .path(ConnectCallResource.class)
                .build();

        return new CallCreator(to, from, uri).create(client);

    }

}
