package co.willsalz.ttyl.application;

import co.willsalz.ttyl.configuration.TTYLConfiguration;
import co.willsalz.ttyl.healthchecks.TwilioHealthCheck;
import co.willsalz.ttyl.middleware.CsrfFilter;
import co.willsalz.ttyl.middleware.TwimlMessageBodyWriter;
import co.willsalz.ttyl.repositories.PhoneNumberRepository;
import co.willsalz.ttyl.resources.v1.ConnectCallResource;
import co.willsalz.ttyl.resources.v1.StartCallResource;
import co.willsalz.ttyl.security.TwilioAuthenticator;
import co.willsalz.ttyl.service.CallService;
import com.twilio.http.TwilioRestClient;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestFilter;

public class TTYLApplication extends Application<TTYLConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(TTYLApplication.class);

    public static void main(final String[] args) throws Exception {
        new TTYLApplication().run(args);
    }

    @Override
    public String getName() {
        return "ttyl";
    }

    @Override
    public void initialize(final Bootstrap<TTYLConfiguration> bootstrap) {
        super.initialize(bootstrap);

        // Allow Environment Variables to be used in YAML
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(true)
                )
        );

        // Views
        bootstrap.addBundle(new AssetsBundle(
                "/static/",
                "/",
                "index.html"
        ));
    }

    public void run(final TTYLConfiguration cfg, final Environment env) throws Exception {

        // Create Inbound Auth Filter
        final ContainerRequestFilter authFilter = new BasicCredentialAuthFilter.Builder<PrincipalImpl>()
                .setAuthenticator(
                        new TwilioAuthenticator(
                                cfg.getAuthenticationConfiguration()
                                        .get("twilio")
                                        .orElseThrow(IllegalArgumentException::new)
                        )
                )
                .setRealm("twilio")
                .buildAuthFilter();

        // Register Middleware
        env.jersey().register(new TwimlMessageBodyWriter());
        env.jersey().register(new CsrfFilter());
        env.jersey().register(new AuthDynamicFeature(authFilter));

        // Repositories + Gateways
        final PhoneNumberRepository phoneNumbers = new PhoneNumberRepository(
                cfg.getTwilioConfiguration().getPhoneNumbers()
        );

        // Services
        final TwilioRestClient twilio = cfg.getTwilioConfiguration().build(env);
        final CallService callService = new CallService(
                twilio,
                cfg.getServiceConfiguration().getBaseUri(),
                cfg.getAuthenticationConfiguration()
                        .get("twilio")
                        .orElseThrow(IllegalArgumentException::new),
                phoneNumbers
        );

        // Register Resources
        env.jersey().register(new StartCallResource(callService));
        env.jersey().register(new ConnectCallResource());

        // Register Healthchecks
        env.healthChecks().register("twilio", new TwilioHealthCheck(twilio));

    }
}
