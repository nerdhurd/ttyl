package co.willsalz.ttyl.service;

import co.willsalz.ttyl.repositories.PhoneNumberRepository;
import com.google.common.collect.ImmutableList;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jackson.Jackson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CallServiceTest {

    private final TwilioRestClient client = mock(TwilioRestClient.class);
    private final URI baseUri = UriBuilder.fromUri("http://localhost").build();
    private final BasicCredentials credentials = new BasicCredentials("username", "password");
    private final PhoneNumberRepository phoneNumbers = new PhoneNumberRepository(ImmutableList.of(new PhoneNumber("18008675309")));
    private final CallService callService = new CallService(client, baseUri, credentials, phoneNumbers);

    @Before
    public void setUp() throws Exception {
        when(client.request(any(Request.class)))
                .thenReturn(new Response("{\"accountSid\": \"someSid\"}", 200));

        when(client.getRegion()).thenReturn("someRegion");

        when(client.getObjectMapper()).thenReturn(Jackson.newObjectMapper());
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(client);
        reset(client);
    }

    @Test
    public void makeCall() throws Exception {
        final String to = "314-694-1000";
        final Call call = callService.makeCall(to);

        assertThat(call).isNotNull();
        assertThat(call.getAccountSid()).isEqualTo("someSid");

        verify(client, times(1)).getAccountSid();
        verify(client, times(1)).getRegion();
        verify(client, times(1)).request(any(Request.class));
        verify(client, times(1)).getObjectMapper();
    }

}