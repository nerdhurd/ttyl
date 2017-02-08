package co.willsalz.ttyl.gateways;

import co.willsalz.ttyl.entities.Representatives;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.ImmutableDescriptor;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RepresentativeGateway {
    private static final Logger logger = LoggerFactory.getLogger(RepresentativeGateway.class);

    private final Client client;
    private final String baseUri;

    public RepresentativeGateway(final Client client, final String baseUri) {
        this.client = client;
        this.baseUri = baseUri;
    }

    public static class BadZipcodeException extends Exception {
        public BadZipcodeException(String message) {
            super(message);
        }
    }

    public Representatives getRepresentativesByZip(String zip) throws BadZipcodeException {

        final Response response = client.target(baseUri)
                // TODO(willsalz): configurable or hard-coded
                .path("getall_mems.php")
                .queryParam("zip", zip)
                .queryParam("output", "json")
                .request(MediaType.APPLICATION_JSON)
                .get();

        // HACK(willsalz): WIMR.com returns the wrong content type so force it
        // to JSON so we can deserialize
        response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        try {
            return response.readEntity(Representatives.class);
        } catch (ProcessingException jsonException) {
            throw new BadZipcodeException("That zipcode was bad");
        }


    }


}
