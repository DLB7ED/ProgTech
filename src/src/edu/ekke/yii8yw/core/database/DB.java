package edu.ekke.yii8yw.core.database;

import edu.ekke.yii8yw.core.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    Connection activeConn;

    public DB(Connection activeConn) {
        this.activeConn = activeConn;
    }
    public DB(){
        Config config = Config.getInstance();
        try {
            this.activeConn = DriverManager.getConnection(config.getDbConnectionURL(), config.getDbUser(), config.getDbPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
