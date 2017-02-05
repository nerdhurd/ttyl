package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class Representatives {
    @NotEmpty
    private final List<Representative> representatives;

    @JsonCreator
    public Representatives(@JsonProperty("results") List<Representative> representatives) {
        this.representatives = representatives;
    }

    public List<Representative> getRepresentatives() {
        return representatives;
    }
}
