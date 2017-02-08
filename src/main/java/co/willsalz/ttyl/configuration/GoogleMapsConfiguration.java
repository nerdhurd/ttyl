package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.maps.GeoApiContext;
import org.hibernate.validator.constraints.NotEmpty;

public class GoogleMapsConfiguration {
    @NotEmpty
    private final String apiKey;

    @JsonCreator
    public GoogleMapsConfiguration(@JsonProperty("apiKey") String apiKey) {
        this.apiKey = apiKey;
    }

    public GeoApiContext getGeoApiContext() {
        return new GeoApiContext().setApiKey(apiKey).setChannel("ttyl");
    }
}
