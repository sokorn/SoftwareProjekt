package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * stellt Methoden zum Hashen von Passwörtern bereit.
 *
 */
public class Password {

    /**
     * öffentliche Methode, erhöht, die Sicherheit, da sie nicht direkt in
     * anderen Klassen aufgerufen wird
     *
     * @param passwordToHash
     * @return
     */
    public static String hashPassword(String passwordToHash) {
        return hashing(passwordToHash);
    }

    private static String hashing(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return generatedPassword;
    }

}
