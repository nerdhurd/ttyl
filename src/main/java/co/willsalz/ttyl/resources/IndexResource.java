package co.willsalz.ttyl.resources;

import co.willsalz.ttyl.views.IndexView;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @GET
    @Timed
    public IndexView getIndex() throws IOException {
        return new IndexView();
    }

}
