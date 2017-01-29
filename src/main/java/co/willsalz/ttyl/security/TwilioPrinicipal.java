package co.willsalz.ttyl.security;

import java.security.Principal;

public class TwilioPrinicipal implements Principal {

    private static final String NAME = "twilio";
    public static final TwilioPrinicipal INSTANCE = new TwilioPrinicipal();

    private TwilioPrinicipal() {
        // Prevent instantiation
    }

    @Override
    public String getName() {
        return NAME;
    }
}
