package co.willsalz.ttyl.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *<result>
 <rep name="Austin Scott" party="R" state="GA" district="8" phone="202-225-6531" office="2417 Rayburn House Office Building" link="http://austinscott.house.gov"/>
 <rep name="John Isakson" party="R" state="GA" district="Senior Seat" phone="202-224-3643" office="131 Russell Senate Office Building" link="http://www.isakson.senate.gov"/>
 <rep name="David Perdue" party="R" state="GA" district="Junior Seat" phone="202-224-3521" office="383 Russell Senate Office Building" link="http://www.perdue.senate.gov"/>
 </result>
 */
public class RepresentativeInfo {

    private String name, party, district, phone, office, link;

    @JsonCreator
    public RepresentativeInfo(@JsonProperty("name") String name,
                              @JsonProperty("party") String party,
                              @JsonProperty("district") String district,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("office") String office,
                              @JsonProperty("link") String link) {
        this.name = name;
        this.party = party;
        this.district = district;
        this.phone = phone;
        this.office = office;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
