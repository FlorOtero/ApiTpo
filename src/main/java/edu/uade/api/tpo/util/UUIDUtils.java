package edu.uade.api.tpo.util;

import java.util.UUID;

public class UUIDUtils {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
