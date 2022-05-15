package edu.ekke.yii8yw.helpers;

import org.junit.jupiter.api.Assertions;

class EncryptedPasswordTest {

    @org.junit.jupiter.api.Test
    void verify() {
        try {
            Assertions.assertEquals(true, EncryptedPassword.verify("secret12-H", "e698f9b11b733250cae4b8c9d965a86f97943bf0"));
            Assertions.assertNotEquals(true, EncryptedPassword.verify("secret12H", "e698f9b11b733250cae4b8c9d965a86f97943bf0"));
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @org.junit.jupiter.api.Test
    void hash() {
        try {
            Assertions.assertEquals("e698f9b11b733250cae4b8c9d965a86f97943bf0", EncryptedPassword.hash("secret12-H"));
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertThrows(Exception.class, () -> {EncryptedPassword.hash("secret");}, "Weak password");
    }
}