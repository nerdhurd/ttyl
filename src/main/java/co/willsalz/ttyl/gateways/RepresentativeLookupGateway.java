package co.willsalz.ttyl.gateways;

import co.willsalz.ttyl.entities.RepresentativeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.representer.Represent;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ahb5 on 1/27/17.
 */
public class RepresentativeLookupGateway {
    private final Client client;
    private final String baseUri;
    private final ObjectMapper objectMapper;

    public RepresentativeLookupGateway(Client client, String baseUri, ObjectMapper objectMapper) {
        this.client = client;
        this.baseUri = baseUri;
        this.objectMapper = objectMapper;
    }

    public List<RepresentativeInfo> getRepresentativesByZip(String zip) {
        Response response = client.target(baseUri)
                .path("getall_mems.php")
                .queryParam("zip", zip)
                .queryParam("output", "json")
                .request(MediaType.APPLICATION_JSON).get();

        new TypeReference<>()
        objectMapper.readValue(response.readEntity(String.class), new GenericType<List>(RepresentativeInfo.class));
        response.getEntity();
    }
}
