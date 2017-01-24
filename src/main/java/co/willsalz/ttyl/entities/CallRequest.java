package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class CallRequest {
    @NotEmpty
    private final String from;

    @NotEmpty
    private final String to;

    @JsonCreator
    public CallRequest(@JsonProperty("phone-number") final String from,
                       @JsonProperty("zip") final String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
