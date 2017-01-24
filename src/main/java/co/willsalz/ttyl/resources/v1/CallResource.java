package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.service.PhoneService;
import co.willsalz.ttyl.views.SuccessView;
import com.codahale.metrics.annotation.Timed;
import com.twilio.rest.api.v2010.account.Call;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("v1")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public class CallResource {

    private final PhoneService phoneService;

    public CallResource(final PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @POST
    @Path("call")
    @Timed
    public SuccessView makeCall(@NotEmpty @FormParam("phone-number") String phoneNumber,
                                @NotEmpty @FormParam("zip") String zip) {

        final Call call = phoneService.makeCall(phoneNumber);

        return new SuccessView();

    }

}
