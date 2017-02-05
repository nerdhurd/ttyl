package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <result>
 * <rep name="Austin Scott" party="R" state="GA" district="8" phone="202-225-6531" office="2417 Rayburn House Office Building" link="http://austinscott.house.gov"/>
 * <rep name="John Isakson" party="R" state="GA" district="Senior Seat" phone="202-224-3643" office="131 Russell Senate Office Building" link="http://www.isakson.senate.gov"/>
 * <rep name="David Perdue" party="R" state="GA" district="Junior Seat" phone="202-224-3521" office="383 Russell Senate Office Building" link="http://www.perdue.senate.gov"/>
 * </result>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Representative {

    private final String name, party, district, phone, office, link, state;

    @JsonCreator
    public Representative(@JsonProperty("name") String name,
                          @JsonProperty("party") String party,
                          @JsonProperty("district") String district,
                          @JsonProperty("phone") String phone,
                          @JsonProperty("office") String office,
                          @JsonProperty("state") String state,
                          @JsonProperty("link") String link) {
        this.name = name;
        this.party = party;
        this.district = district;
        this.phone = phone;
        this.office = office;
        this.state = state;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getDistrict() {
        return district;
    }

    public String getPhone() {
        return phone;
    }

    public String getOffice() {
        return office;
    }

    public String getLink() {
        return link;
    }

    public String getState() {
        return state;
    }
}
