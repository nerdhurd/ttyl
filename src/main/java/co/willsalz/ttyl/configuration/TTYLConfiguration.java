package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TTYLConfiguration extends Configuration {

    @Valid
    @NotNull
    private final TwilioConfiguration twilioConfiguration;

    @Valid
    @NotNull
    private final JerseyClientConfiguration jerseyClientConfiguration;

    @Valid
    @NotNull
    private final RepresentativeClientConfiguration representativeClientConfiguration;

    @JsonCreator
    public TTYLConfiguration(
            @JsonProperty("twilio") final TwilioConfiguration twilioConfiguration,
            @JsonProperty("httpClient") final JerseyClientConfiguration jerseyClientConfiguration,
            @JsonProperty("representativeClient") final RepresentativeClientConfiguration representativeClientConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        this.jerseyClientConfiguration = jerseyClientConfiguration;
        this.representativeClientConfiguration = representativeClientConfiguration;
    }

    public RepresentativeClientConfiguration getRepresentativeClientConfiguration() {
        return representativeClientConfiguration;
    }

    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }

    public TwilioConfiguration getTwilioConfiguration() {
        return twilioConfiguration;
    }

}
