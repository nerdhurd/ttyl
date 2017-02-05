package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.Representatives;
import co.willsalz.ttyl.gateways.RepresentativeLookupGateway;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("v1/representatives")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RepresentativeResource {
    private RepresentativeLookupGateway representativeLookupGateway;

    public RepresentativeResource(RepresentativeLookupGateway representativeLookupGateway) {
        this.representativeLookupGateway = representativeLookupGateway;
    }

    @GET
    public Representatives getRepresentatives(@QueryParam("zip") @NotEmpty String zip) {
        return representativeLookupGateway.getRepresentativesByZip(zip);
    }
}
