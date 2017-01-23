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

    @JsonCreator
    public TTYLConfiguration(@JsonProperty("twilio") final TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    public TwilioConfiguration getTwilioConfiguration() {
        return twilioConfiguration;
    }

}
