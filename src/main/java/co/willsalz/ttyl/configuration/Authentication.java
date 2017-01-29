package co.willsalz.ttyl.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Authentication {

    @NotEmpty
    private final String twilioUsername;

    @NotEmpty
    private final String twilioPassword;

    @JsonCreator
    public Authentication(@JsonProperty("twilioUsername") String twilioUsername,
                          @JsonProperty("twilioPassword") String twilioPassword) {
        this.twilioUsername = twilioUsername;
        this.twilioPassword = twilioPassword;
    }

    public String getTwilioUsername() {
        return twilioUsername;
    }

    public String getTwilioPassword() {
        return twilioPassword;
    }
}
