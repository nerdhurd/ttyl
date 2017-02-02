package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.http.TwilioRestClient;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class TwilioConfiguration {

    @NotEmpty
    private final String accountSid;

    @NotEmpty
    private final String authToken;

    @NotEmpty
    private final List<String> phoneNumbers;

    @JsonCreator
    public TwilioConfiguration(@JsonProperty("accountSid") String accountSid,
                               @JsonProperty("authToken") String authToken,
                               @JsonProperty("phoneNumbers") List<String> phoneNumbers) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.phoneNumbers = phoneNumbers;
    }

    public TwilioRestClient build(final Environment environment) {
        return new TwilioRestClient.Builder(accountSid, authToken)
                .build();
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}
