package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.entities.StartCallRequest;
import co.willsalz.ttyl.service.CallService;
import com.twilio.rest.api.v2010.account.Call;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

public class StartCallResourceTest {

    private final String somePhoneNumber = "1 (800) 934-6489";
    private String anotherPhoneNumber = "18001234567";
    private final CallService callService = mock(CallService.class);
    private final Call call = mock(Call.class);
    private final Jedis jedis = mock(Jedis.class);
    private final JedisPool jedisPool = mock(JedisPool.class);

    @Rule
    public final ResourceTestRule resource = ResourceTestRule
            .builder()
            .addResource(new StartCallResource(callService, jedisPool))
            .build();

    @Before
    public void setUp() throws Exception {
        when(callService.makeCall(anyString())).thenReturn(call);
        when(jedisPool.getResource()).thenReturn(jedis);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(callService, call);
        reset(callService, call);
    }

    @Test
    public void makeCall() throws Exception {

        final Response res = resource.client()
                .target(UriBuilder.fromPath("/v1/call"))
                .request()
                .post(Entity.json(new StartCallRequest(
                        somePhoneNumber,
                        anotherPhoneNumber
                )));

        assertThat(res.getStatus()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());

        verify(callService, times(1)).makeCall(somePhoneNumber);

    }

}