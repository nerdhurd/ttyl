package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.RepresentativeInfo;
import co.willsalz.ttyl.gateways.RepresentativeLookupGateway;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ahb5 on 1/27/17.
 */
@Path("v1/representatives")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RepresentativeResource {
    private RepresentativeLookupGateway representativeLookupGateway;

    public RepresentativeResource(RepresentativeLookupGateway representativeLookupGateway) {
        this.representativeLookupGateway = representativeLookupGateway;
    }

    @GET
    public List<RepresentativeInfo> getRepresentatives(@QueryParam("zip") @NotEmpty String zip) {
        return representativeLookupGateway.getRepresentativesByZip(zip);
    }
}
