package co.willsalz.ttyl.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {
    private final double latitude;
    private final double longitude;

    @JsonCreator
    public Coordinate(@JsonProperty("latitude") final double latitude,
                      @JsonProperty("longitude") final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
