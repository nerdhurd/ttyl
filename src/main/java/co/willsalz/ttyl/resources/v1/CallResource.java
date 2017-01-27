package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.CallRequest;
import co.willsalz.ttyl.service.PhoneService;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;
import com.twilio.rest.api.v2010.account.Call;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CallResource {

    private final PhoneService phoneService;

    public CallResource(final PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @POST
    @Path("call")
    @Timed
    public Response makeCall(@NotNull @Valid final CallRequest callRequest) {

        final Call call = phoneService.makeCall(callRequest.getTo());

        return Response.ok()
                .entity(Entity.json(ImmutableMap.of("status", Response.Status.OK)))
                .build();

    }

}
