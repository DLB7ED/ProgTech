package edu.ekke.yii8yw.core.database;

import java.util.ArrayList;
import java.util.HashMap;

public class DB implements IDatabaseDriver{

    protected static DB instance;

    IDatabaseDriver driver;

    public static DB getInstance() {
        return instance;
    }

    public DB(IDatabaseDriver driver) {
        this.driver = driver;
        instance = this;
    }

    @Override
    public HashMap<String, Object> findOne(String query, ArrayList<Object> args) {
        return this.driver.findOne(query, args);
    }

    @Override
    public ResultTable findAll(String query, ArrayList<Object> args) {
        return this.driver.findAll(query, args);
    }

    @Override
    public int execute(String query, ArrayList<Object> args) {
        return this.driver.execute(query, args);
    }
}
