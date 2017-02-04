package co.willsalz.ttyl.configuration;

import com.bendb.dropwizard.redis.JedisFactory;
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
    private final AuthenticationConfiguration authenticationConfiguration;

    @Valid
    @NotNull
    private final ServiceConfiguration serviceConfiguration;

    @Valid
    @NotNull
    private final JedisFactory jedisFactory;

    @Valid
    @NotNull
    private final JerseyClientConfiguration jerseyClientConfiguration;

    @Valid
    @NotNull
    private final RepresentativeClientConfiguration representativeClientConfiguration;

    @JsonCreator
    public TTYLConfiguration(
            @JsonProperty("twilio") final TwilioConfiguration twilioConfiguration,
            @JsonProperty("authentication") final AuthenticationConfiguration authenticationConfiguration,
            @JsonProperty("service") final ServiceConfiguration serviceConfiguration,
            @JsonProperty("redis") final JedisFactory jedisFactory,
            @JsonProperty("httpClient") final JerseyClientConfiguration jerseyClientConfiguration,
            @JsonProperty("representativeClient") final RepresentativeClientConfiguration representativeClientConfiguration
    ) {
        this.twilioConfiguration = twilioConfiguration;
        this.authenticationConfiguration = authenticationConfiguration;
        this.serviceConfiguration = serviceConfiguration;
        this.jedisFactory = jedisFactory;
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

    public AuthenticationConfiguration getAuthenticationConfiguration() {
        return authenticationConfiguration;
    }

    public ServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }

    public JedisFactory getJedisFactory() {
        return jedisFactory;
    }
}
