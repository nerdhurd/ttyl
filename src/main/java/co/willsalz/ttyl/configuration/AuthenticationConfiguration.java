package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.auth.basic.BasicCredentials;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;
import java.util.Optional;

public class AuthenticationConfiguration {

    @NotEmpty
    private final Map<String, String> credentials;

    @JsonCreator
    public AuthenticationConfiguration(@JsonProperty("credentials") Map<String, String> credentials) {
        this.credentials = credentials;
    }

    public Optional<BasicCredentials> get(final String username) {
        final Optional<String> password = Optional.ofNullable(credentials.get(username));
        return password.map(p -> new BasicCredentials(username, p));
    }
}
