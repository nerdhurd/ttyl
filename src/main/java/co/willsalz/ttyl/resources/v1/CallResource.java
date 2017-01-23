package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.CallRequest;
import co.willsalz.ttyl.resources.IndexResource;
import co.willsalz.ttyl.service.PhoneService;
import com.codahale.metrics.annotation.Timed;
import com.twilio.rest.api.v2010.account.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("v1")
@Produces(MediaType.TEXT_HTML)
@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
public class CallResource {

    private static final Logger logger = LoggerFactory.getLogger(CallResource.class);
    private final PhoneService phoneService;

    public CallResource(final PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @POST
    @Timed
    @Path("call")
    public Response makeCall(@NotNull @Valid final CallRequest request) {

        logger.debug("Call Request: {}", request);
        final Call call = phoneService.makeCall(
                request.getFrom(),
                request.getTo()
        );
        logger.debug("Call: {}", call);

        return Response.temporaryRedirect(UriBuilder.fromResource(IndexResource.class).build()).build();

    }

}
