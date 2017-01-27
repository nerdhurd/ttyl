package co.willsalz;


import co.willsalz.ttyl.application.TTYLApplication;
import co.willsalz.ttyl.configuration.TTYLConfiguration;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class TTYLApplicationTest {

    @ClassRule
    public static final DropwizardAppRule<TTYLConfiguration> app =
            new DropwizardAppRule<>(TTYLApplication.class, ResourceHelpers.resourceFilePath("config/test.yaml"));

    @Test
    public void testCSRF() {
        final Client client = new JerseyClientBuilder(app.getEnvironment()).build("test client");

        final Response response = client
                .target(UriBuilder.fromUri("http://localhost").port(app.getLocalPort()).path("/api/v1/call"))
                .request()
                .post(Entity.json(ImmutableMap.of("to", "somePhoneNumber")));

        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }
}
