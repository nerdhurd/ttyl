package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class CallRequest {
    @NotEmpty
    private final String to;

    @JsonCreator
    public CallRequest(@JsonProperty("to") final String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }
}
