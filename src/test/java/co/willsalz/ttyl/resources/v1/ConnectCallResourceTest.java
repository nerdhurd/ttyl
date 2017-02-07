package co.willsalz.ttyl.resources.v1;

import co.willsalz.ttyl.middleware.TwimlMessageBodyWriter;
import co.willsalz.ttyl.security.TwilioAuthenticator;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ConnectCallResourceTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String REALM = "twilio";

    private final Base64.Encoder encoder = Base64.getEncoder();

    private final Jedis jedis = mock(Jedis.class);
    private final JedisPool redisPool = mock(JedisPool.class);

    @Rule
    public ResourceTestRule rule = ResourceTestRule
            .builder()
            .setTestContainerFactory(new InMemoryTestContainerFactory())
            .addProvider(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<PrincipalImpl>()
                    .setAuthenticator(new TwilioAuthenticator(new BasicCredentials(USERNAME, PASSWORD)))
                    .setRealm(REALM)
                    .buildAuthFilter()))
            .addProvider(new TwimlMessageBodyWriter())
            .addProvider(RolesAllowedDynamicFeature.class)
            .addResource(new ConnectCallResource(redisPool))
            .build();

    @Before
    public void setUp() throws Exception {
        when(redisPool.getResource()).thenReturn(jedis);
        when(jedis.get(anyString())).thenReturn("someValue");
        verifyNoMoreInteractions(jedis, redisPool);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testNoCredentials() throws Exception {
        final Response response = rule.client()
                .target("/v1/connectCall")
                .request(MediaType.APPLICATION_XML)
                .post(Entity.form(new Form("foo", "bar")));

        assertThat(response.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());
    }

    @Test
    public void testValidCredentials() throws Exception {

        final String authorization = String.format(
                "Basic %s", encoder.encodeToString(String.format("%s:%s", USERNAME, PASSWORD).getBytes())
        );
        final Response response = rule.client()
                .target("/v1/connectCall")
                .request(MediaType.APPLICATION_XML)
                .header("Authorization", authorization)
                .post(Entity.form(new Form("CallSid", "abc123")));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        final String body = response.readEntity(String.class);
        assertThat(body).contains("<Number>someValue</Number>");

        verify(jedis, times(1)).get(anyString());
        verify(redisPool, times(1)).getResource();
    }

}