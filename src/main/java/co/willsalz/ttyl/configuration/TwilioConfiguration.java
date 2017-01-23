package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.http.TwilioRestClient;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

public class TwilioConfiguration {

    @NotEmpty
    private final String accountSid;

    @NotEmpty
    private final String authToken;

    @JsonCreator
    public TwilioConfiguration(@JsonProperty("accountSid") String accountSid,
                               @JsonProperty("authToken") String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    public TwilioRestClient build(final Environment environment) {
        return new TwilioRestClient.Builder(accountSid, authToken)
                .build();
    }
}
