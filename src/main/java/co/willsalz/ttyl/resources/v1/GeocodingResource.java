package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.Geocode;
import co.willsalz.ttyl.entities.GeocodeResults;
import co.willsalz.ttyl.service.GeocodingService;
import com.codahale.metrics.annotation.Timed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("v1/geocode")
@Produces(MediaType.APPLICATION_JSON)
public class GeocodingResource {

    private final GeocodingService geocodingService;

    public GeocodingResource(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @Timed
    @GET
    public GeocodeResults forwardGeocode(@NotEmpty
                                         @QueryParam("address")
                                         final String address) throws Exception {
        final List<Geocode> geocodes = this.geocodingService.forwardGeocode(address);
        return new GeocodeResults(geocodes);
    }

}
