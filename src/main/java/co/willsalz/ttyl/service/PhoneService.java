package co.willsalz.ttyl.service;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class PhoneService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    private final TwilioRestClient client;
    private final PhoneNumber from_;

    public PhoneService(final TwilioRestClient client, final String phoneNumber) {
        this.client = client;
        this.from_ = new PhoneNumber(phoneNumber);
    }

    public Call makeCall(final String to) {

        final PhoneNumber to_ = new PhoneNumber(to);
        final URI uri = UriBuilder.fromUri("http://ttyl-9000.herokuapp.com")
                .path("connectCall")
                .build();
        return new CallCreator(to_, from_, uri).create(client);

    }

}
