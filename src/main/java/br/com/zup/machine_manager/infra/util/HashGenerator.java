package br.com.zup.machine_manager.infra.util;

import java.security.SecureRandom;

public class HashGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomHash(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder hash = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            hash.append(CHARACTERS.charAt(index));
        }

        return hash.toString();
    }
}
