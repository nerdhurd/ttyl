package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class RepresentativeClientConfiguration {

    @NotEmpty
    private final String baseUri;

    @JsonCreator
    public RepresentativeClientConfiguration(@JsonProperty("baseUri") String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBaseUri() {
        return baseUri;
    }
}
