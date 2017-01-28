package co.willsalz.ttyl.middleware;

import org.glassfish.jersey.server.filter.CsrfProtectionFilter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class CsrfFilter implements DynamicFeature {

    private static final ContainerRequestFilter CSRF = new CsrfProtectionFilter();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CsrfFilterBypass {
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (!resourceInfo.getResourceClass().isAnnotationPresent(CsrfFilterBypass.class)) {
            context.register(CSRF);
        }
    }
}
