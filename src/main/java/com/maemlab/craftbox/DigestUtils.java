package com.maemlab.craftbox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class DigestUtils {
    private DigestUtils() {}

    /**
     * Compute hex digest (e.g. SHA-256) of the given file
     */
    public static String calculateDigest(Path file, String algorithm) throws NoSuchAlgorithmException, IOException {
        var md = MessageDigest.getInstance(algorithm);
        try (var dis = new DigestInputStream(Files.newInputStream(file), md)) {
            // Read fully to update digest
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1){}
        }
        byte[] hash = md.digest();
        return toHex(hash);
    }

    private static String toHex(byte[] hash) {
        var sb = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
