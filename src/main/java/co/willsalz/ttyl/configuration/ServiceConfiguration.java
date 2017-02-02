package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

public class ServiceConfiguration {

    @Valid
    @NotNull
    private final URI baseUri;

    @JsonCreator
    public ServiceConfiguration(@JsonProperty("baseUri") URI baseUri) {
        this.baseUri = baseUri;
    }

    public URI getBaseUri() {
        return baseUri;
    }

}
