package co.willsalz.ttyl.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.Account;

public class TwilioHealthCheck extends HealthCheck {

    private final TwilioRestClient client;

    public TwilioHealthCheck(final TwilioRestClient client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        final Account account = Account.fetcher().fetch(client);
        switch (account.getStatus()) {
            case ACTIVE:
                return Result.healthy();
            case SUSPENDED:
            case CLOSED:
            default:
                return Result.unhealthy("Account not Active");
        }
    }
}
