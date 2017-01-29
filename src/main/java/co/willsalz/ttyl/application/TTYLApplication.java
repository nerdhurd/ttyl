package co.willsalz.ttyl.application;

import co.willsalz.ttyl.configuration.TTYLConfiguration;
import co.willsalz.ttyl.healthchecks.TwilioHealthCheck;
import co.willsalz.ttyl.middleware.CsrfFilter;
import co.willsalz.ttyl.middleware.TwimlMessageBodyWriter;
import co.willsalz.ttyl.resources.v1.CallResource;
import co.willsalz.ttyl.resources.v1.ConnectCallResource;
import co.willsalz.ttyl.security.TwilioAuthenticator;
import co.willsalz.ttyl.security.TwilioPrinicipal;
import co.willsalz.ttyl.service.PhoneService;
import com.twilio.http.TwilioRestClient;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        // Register Middleware
        env.jersey().register(new TwimlMessageBodyWriter());
        env.jersey().register(new CsrfFilter());
        env.jersey().register(
                new AuthDynamicFeature(
                        new BasicCredentialAuthFilter.Builder<TwilioPrinicipal>()
                                .setAuthenticator(
                                        new TwilioAuthenticator(
                                                new BasicCredentials(
                                                        cfg.getAuth().getTwilioUsername(),
                                                        cfg.getAuth().getTwilioPassword()
                                                )
                                        )
                                )
                                .setRealm("twilio")
                                .buildAuthFilter()
                )
        );

        // Services
        final TwilioRestClient twilio = cfg.getTwilioFactory().build(env);
        final PhoneService phoneService = new PhoneService(
                twilio,
                cfg.getTwilioFactory().getPhoneNumber()
        );

        // Register Resources
        env.jersey().register(new CallResource(phoneService));
        env.jersey().register(new ConnectCallResource());

        // Register Healthchecks
        env.healthChecks().register("twilio", new TwilioHealthCheck(twilio));

    }
}
