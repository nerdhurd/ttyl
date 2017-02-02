package co.willsalz.ttyl.service;

import com.google.common.collect.ImmutableList;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jackson.Jackson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PhoneServiceTest {

    private final List<String> sourcePhoneNumbers = ImmutableList.of("1 (800) 934-6489");
    private final TwilioRestClient client = mock(TwilioRestClient.class);
    private final URI baseUri = UriBuilder.fromUri("http://localhost").build();
    private final BasicCredentials credentials = new BasicCredentials("username", "password");
    private final PhoneService phoneService = new PhoneService(client, baseUri, credentials, sourcePhoneNumbers);

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
    public void testGetRandomFrom() throws Exception {
        assertThat(phoneService.randomFrom()).isEqualTo("1 (800) 934-6489");
    }

    @Test
    public void makeCall() throws Exception {
        final String to = "314-694-1000";
        final Call call = phoneService.makeCall(to);

        assertThat(call).isNotNull();
        assertThat(call.getAccountSid()).isEqualTo("someSid");

        verify(client, times(1)).getAccountSid();
        verify(client, times(1)).getRegion();
        verify(client, times(1)).request(any(Request.class));
        verify(client, times(1)).getObjectMapper();
    }

}