package co.willsalz.ttyl.service;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class PhoneService {

    private final TwilioRestClient client;

    public PhoneService(final TwilioRestClient client) {
        this.client = client;
    }

    public Call makeCall(final String from, final String to) {

        final PhoneNumber to_ = new PhoneNumber(to);
        final PhoneNumber from_ = new PhoneNumber(from);
        final URI uri = URI.create("http://demo.twilio.com/welcome/voice/");

        return new CallCreator(to_, from_, uri).create(client);
    }

}
