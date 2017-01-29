package co.willsalz.ttyl.security;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class TwilioAuthenticator implements Authenticator<BasicCredentials, TwilioPrinicipal> {

    private final BasicCredentials credentials;

    public TwilioAuthenticator(final BasicCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public Optional<TwilioPrinicipal> authenticate(final BasicCredentials inputCredentials) throws AuthenticationException {
        if (this.credentials.equals(inputCredentials)) {
            return Optional.of(TwilioPrinicipal.INSTANCE);
        }
        return Optional.empty();
    }

}
