package edu.ekke.yii8yw.helpers;

import edu.ekke.yii8yw.core.exceptions.ValidatorException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class EncryptedPassword {
    private static String getHashedPassword(String input) {
        final String SALT = "464Igv9gkfILduHrfvkdiwhthXIq0ZtN";
        input = String.format("%s$%s", SALT, input);
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // ignored
        }

        return hash.toString();
    }

    public static boolean verify(String raw, String hash) throws ValidatorException {
        if (Objects.equals(raw, "") || Objects.equals(hash, ""))
        {
            throw new ValidatorException("raw or hash is empty");
        }
        return getHashedPassword(raw).equals(hash);
    }

    public static String hash(String password) throws ValidatorException {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._;,/\\\\#-])[A-Za-z\\d@$!%*?&._;,/\\\\#-]{8,}$")){
            throw new ValidatorException("Weak password");
        }
        return getHashedPassword(password);
    }
}