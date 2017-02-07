package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class StartCallRequest {
    @NotEmpty
    private final String from;

    @NotEmpty
    private final String to;

    @JsonCreator
    public StartCallRequest(@JsonProperty("from_phone_number") final String from,
                            @JsonProperty("to_phone_number") final String to) {
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
