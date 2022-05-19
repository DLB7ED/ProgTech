package edu.ekke.yii8yw.core;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.core.exceptions.DatabaseException;
import edu.ekke.yii8yw.core.exceptions.ValidatorException;
import edu.ekke.yii8yw.helpers.EncryptedPassword;
import edu.ekke.yii8yw.helpers.Tools;
import edu.ekke.yii8yw.models.User;
import jdk.jshell.spi.ExecutionControl;
import org.apache.log4j.Logger;

import java.util.Objects;

public abstract class Auth {
    public static User activeUser;

    public static boolean check(){
        return activeUser != null;
    }

    public static boolean isAdmin() {
        return activeUser != null && activeUser.getIsAdmin();
    }

    public static void register(String name, String email, String password, String passwordConfirmation)
            throws ValidatorException, DatabaseException {
        if(!Objects.equals(passwordConfirmation, password)) {
            throw new ValidatorException("Password and password confirmation does not match");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(EncryptedPassword.hash(password));
        if(!user.save()) {
            Logger.getLogger(Auth.class).error("User registration failed");
            throw new DatabaseException("User save was unsuccessful");
        }
        Auth.login(email, password);
    }

    public static void login(String email, String password) throws ValidatorException {
        User user = new User();
        user.load(email);
        if(!EncryptedPassword.verify(password, user.getPassword())) {
            Logger.getLogger(Auth.class).error("Login error");
            throw new ValidatorException("Wrong username or password");
        }

        activeUser = user;
    }

    public static void logout() {
        activeUser = null;
    }
}
