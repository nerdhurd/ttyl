package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.CallRequest;
import co.willsalz.ttyl.service.PhoneService;
import com.twilio.rest.api.v2010.account.Call;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CallResourceTest {

    private final String somePhoneNumber = "1 (800) 934-6489";
    private final PhoneService phoneService = mock(PhoneService.class);
    private final Call call = mock(Call.class);

    @Rule
    public final ResourceTestRule resource = ResourceTestRule
            .builder()
            .addResource(new CallResource(phoneService))
            .build();

    @Before
    public void setUp() throws Exception {
        when(phoneService.makeCall(anyString())).thenReturn(call);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(phoneService, call);
        reset(phoneService, call);
    }

    @Test
    public void makeCall() throws Exception {

        final Response res = resource.client()
                .target(UriBuilder.fromPath("/v1/call"))
                .request()
                .post(Entity.json(new CallRequest(somePhoneNumber)));

        assertThat(res.getStatus()).isEqualTo(Response.Status.ACCEPTED.getStatusCode());

        verify(phoneService, times(1)).makeCall(somePhoneNumber);

    }

}