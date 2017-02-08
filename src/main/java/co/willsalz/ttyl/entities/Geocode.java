package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Geocode {

    @NotEmpty
    private final String address;

    @Valid
    @NotNull
    private final Coordinate coordiate;

    @JsonCreator
    public Geocode(
            @JsonProperty("address") final String address,
            @JsonProperty("coordinate") final Coordinate coordiate) {
        this.address = address;
        this.coordiate = coordiate;
    }

    public String getAddress() {
        return address;
    }

    public Coordinate getCoordiate() {
        return coordiate;
    }
}
