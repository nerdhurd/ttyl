package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.Representatives;
import co.willsalz.ttyl.gateways.RepresentativeGateway;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/representatives")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RepresentativeResource {
    private RepresentativeGateway representativeGateway;

    public RepresentativeResource(RepresentativeGateway representativeGateway) {
        this.representativeGateway = representativeGateway;
    }

    @GET
    public Representatives getRepresentatives(@QueryParam("zip") @NotEmpty String zip) throws WebApplicationException {
        try {
            return representativeGateway.getRepresentativesByZip(zip);
        } catch (RepresentativeGateway.BadZipcodeException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }
}
