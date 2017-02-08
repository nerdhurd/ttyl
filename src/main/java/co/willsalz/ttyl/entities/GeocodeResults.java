package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GeocodeResults {
    @NotNull
    private final List<Geocode> geocodes;

    @JsonCreator
    public GeocodeResults(@JsonProperty("geocodes") List<Geocode> geocodes) {
        this.geocodes = geocodes;
    }

    public List<Geocode> getGeocodes() {
        return geocodes;
    }
}
