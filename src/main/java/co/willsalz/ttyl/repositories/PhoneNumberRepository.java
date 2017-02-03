package co.willsalz.ttyl.repositories;

import com.twilio.type.PhoneNumber;

import java.util.List;
import java.util.Random;

public class PhoneNumberRepository {

    private final Random rng = new Random(System.currentTimeMillis());
    private final List<PhoneNumber> phoneNumbers;

    public PhoneNumberRepository(final List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumbers.get(rng.nextInt(phoneNumbers.size()));
    }
}
