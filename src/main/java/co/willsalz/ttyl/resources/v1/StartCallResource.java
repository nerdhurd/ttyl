package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.StartCallRequest;
import co.willsalz.ttyl.service.CallService;
import com.codahale.metrics.annotation.Timed;
import com.twilio.rest.api.v2010.account.Call;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/call")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StartCallResource {

    private final CallService callService;

    public StartCallResource(final CallService callService) {
        this.callService = callService;
    }

    @POST
    @Timed
    public Response startCall(@NotNull @Valid final StartCallRequest startCallRequest) {

        final Call call = callService.makeCall(startCallRequest.getTo());

        // TODO(wjs): store call [ phone #, zip, etc ] in ephemeral DB for later retrival

        return Response.noContent().build();

    }

}
