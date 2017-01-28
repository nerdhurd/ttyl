package co.willsalz.ttyl.middleware;

import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Produces(MediaType.APPLICATION_XML)
public class TwimlMessageBodyWriter implements MessageBodyWriter<TwiML> {

    private static final Logger logger = LoggerFactory.getLogger(TwimlMessageBodyWriter.class);

    @Override
    public boolean isWriteable(final Class<?> type,
                               final Type genericType,
                               final Annotation[] annotations,
                               final MediaType mediaType) {

        // Make sure we can actually write the object being passed in
        return TwiML.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(final TwiML twiML,
                        final Class<?> type,
                        final Type genericType,
                        final Annotation[] annotations,
                        final MediaType mediaType) {

        // Recommend from the Jersey Docs
        return -1;
    }

    @Override
    public void writeTo(final TwiML twiML,
                        final Class<?> type,
                        final Type genericType,
                        final Annotation[] annotations,
                        final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders,
                        final OutputStream entityStream) throws IOException, WebApplicationException {

        // Write XML out
        try (final OutputStreamWriter writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8)) {
            final String body = twiML.toXml();
            writer.write(body);
        } catch (final TwiMLException e) {
            logger.warn("Error converting TwiML to XML", e);
            throw new WebApplicationException(e);
        }

    }
}
