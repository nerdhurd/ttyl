package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwilioCallRequest {

    @NotEmpty
    private final String callSid;

    @NotEmpty
    private final String from;

    @NotEmpty
    private final String to;

    @JsonCreator
    public TwilioCallRequest(@JsonProperty("CallSid") final String callSid,
                             @JsonProperty("From") final String from,
                             @JsonProperty("To") final String to) {
        this.callSid = callSid;
        this.from = from;
        this.to = to;
    }

    public String getCallSid() {
        return callSid;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

}
