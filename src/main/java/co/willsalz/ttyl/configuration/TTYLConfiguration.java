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
    private final AuthenticationConfiguration authenticationConfiguration;

    @Valid
    @NotNull
    private final ServiceConfiguration serviceConfiguration;

    @JsonCreator
    public TTYLConfiguration(
            @JsonProperty("twilio") final TwilioConfiguration twilioConfiguration,
            @JsonProperty("authentication") final AuthenticationConfiguration authenticationConfiguration,
            @JsonProperty("service") final ServiceConfiguration serviceConfiguration
    ) {
        this.twilioConfiguration = twilioConfiguration;
        this.authenticationConfiguration = authenticationConfiguration;
        this.serviceConfiguration = serviceConfiguration;
    }

    public TwilioConfiguration getTwilioConfiguration() {
        return twilioConfiguration;
    }

    public AuthenticationConfiguration getAuthenticationConfiguration() {
        return authenticationConfiguration;
    }

    public ServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }
}
