package co.willsalz.ttyl.service;

import co.willsalz.ttyl.entities.Geocode;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import mappers.GeocodeMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeocodingService {

    private final GeoApiContext ctx;

    public GeocodingService(GeoApiContext ctx) {
        this.ctx = ctx;
    }

    public List<Geocode> forwardGeocode(final String address) throws Exception {
        // Create Request
        final GeocodingApiRequest req = GeocodingApi.newRequest(ctx).address(address);

        // Execute request
        final GeocodingResult[] geocodingResults = req.await();

        // Map to internal type
        return Arrays.stream(geocodingResults)
                .filter((g) -> !g.partialMatch)
                .map(GeocodeMapper::fromGoogleGeocodingResult)
                .collect(Collectors.toList());
    }
}
