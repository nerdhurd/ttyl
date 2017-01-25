package co.willsalz.ttyl.application;

import co.willsalz.ttyl.configuration.TTYLConfiguration;
import co.willsalz.ttyl.healthchecks.TwilioHealthCheck;
import co.willsalz.ttyl.resources.IndexResource;
import co.willsalz.ttyl.resources.v1.CallResource;
import co.willsalz.ttyl.service.PhoneService;
import com.twilio.http.TwilioRestClient;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
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
        bootstrap.addBundle(new ViewBundle<>());
    }

    public void run(final TTYLConfiguration cfg, final Environment env) throws Exception {

        // Services
        final TwilioRestClient twilio = cfg.getTwilioConfiguration().build(env);
        final PhoneService phoneService = new PhoneService(
                twilio,
                cfg.getTwilioConfiguration().getPhoneNumber()
        );

        // Register Resources
        env.jersey().register(new IndexResource());
        env.jersey().register(new CallResource(phoneService));

        // Register Endpoints
        env.healthChecks().register("twilio", new TwilioHealthCheck(twilio));

    }
}
