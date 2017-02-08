package mappers;

import co.willsalz.ttyl.entities.Coordinate;
import co.willsalz.ttyl.entities.Geocode;
import com.google.maps.model.GeocodingResult;

public class GeocodeMapper {

    private GeocodeMapper() {
        // Prevent accidental instantiation
    }

    public static Geocode fromGoogleGeocodingResult(final GeocodingResult res) {
        return new Geocode(
                res.formattedAddress,
                new Coordinate(
                        res.geometry.location.lat,
                        res.geometry.location.lng
                )
        );
    }
}
