package com.github.oscalitemc.discipline.api.utility;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Discipline's implementation of 'IP Obfuscation' a process which allows server operators
 * to hide player IP Addresses from moderators, without preventing them from identifying clients
 * by IP Address.
 *
 * This implementation must not provide a method of reversing the generated string
 * @since 0.0.1
 * */
public class IPObfuscator {

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();

    private final Random random;
    private final int length;

    public IPObfuscator() {
        this.random = new Random();
        this.length = 8;
    }

    /**
     * Generate a random IPObfuscator#length alphanumeric String to represent an IP Address
     * and add said IPAddress to a lookup table
     * */
    public String obfuscate(String address) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Add a random entry from the alphabet
            builder.append(ALPHABET[random.nextInt(ALPHABET.length)-1]);
        }

        // TODO enter address, builder.toString pairing to a persistent lookup table.
        // TODO If there's a collision, run recursively until resolved.

        return builder.toString();
    }

    public String get(String address) {
        // TODO get from lookup table, if not present obfuscate and place in table.
        return obfuscate(address);
    }
}
