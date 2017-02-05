package co.willsalz.ttyl.gateways;

import co.willsalz.ttyl.entities.Representatives;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RepresentativeLookupGateway {
    private static final Logger logger = LoggerFactory.getLogger(RepresentativeLookupGateway.class);
    private final Client client;
    private final String baseUri;
    private final ObjectMapper objectMapper;

    public RepresentativeLookupGateway(Client client, String baseUri, ObjectMapper objectMapper) {
        this.client = client;
        this.baseUri = baseUri;
        this.objectMapper = objectMapper;
    }

    public Representatives getRepresentativesByZip(String zip) {
        Response response =  client.target(baseUri)
                .path("getall_mems.php")
                .queryParam("zip", zip)
                .queryParam("output", "json")
                .request(MediaType.APPLICATION_JSON)
                .get();
        response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        return response.readEntity(Representatives.class);
    }


}
