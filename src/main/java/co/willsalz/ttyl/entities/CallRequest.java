package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CallRequest {
    private final String from;
    private final String to;

    @JsonCreator
    public CallRequest(@JsonProperty("from") final String from,
                       @JsonProperty("to") final String to) {
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
