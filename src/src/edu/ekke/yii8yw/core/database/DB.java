package edu.ekke.yii8yw.core.database;

import java.util.ArrayList;
import java.util.HashMap;

public class DB implements IDatabaseDriver{

    IDatabaseDriver driver;

    public DB(IDatabaseDriver driver) {
        this.driver = driver;
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
