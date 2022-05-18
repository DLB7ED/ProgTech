package edu.ekke.yii8yw.core;

public class Config {
    private static final Config instance = new Config();
    private String dbConnectionURL = "mysql://localhost:3306/progtech";

    public String getDbConnectionURL() {
        return dbConnectionURL;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    private String dbUser = "root";
    private String dbPassword = "";

    protected Config() {}

    public static Config getInstance() {
        return instance;
    }
}
