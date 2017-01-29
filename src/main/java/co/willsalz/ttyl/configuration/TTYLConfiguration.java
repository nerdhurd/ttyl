package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TTYLConfiguration extends Configuration {

    @Valid
    @NotNull
    private final TwilioConfiguration twilioConfiguration;

    @Valid
    @NotNull
    private final Authentication authentication;

    @JsonCreator
    public TTYLConfiguration(@JsonProperty("twilio") final TwilioConfiguration twilioConfiguration,
                             @JsonProperty("authentication") final Authentication authentication) {
        this.twilioConfiguration = twilioConfiguration;
        this.authentication = authentication;
    }

    public TwilioConfiguration getTwilioFactory() {
        return twilioConfiguration;
    }

    public Authentication getAuth() {
        return authentication;
    }
}
