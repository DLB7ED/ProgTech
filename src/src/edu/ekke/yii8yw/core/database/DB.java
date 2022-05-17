package edu.ekke.yii8yw.core.database;

import edu.ekke.yii8yw.models.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class DB implements IDatabaseDriver{

    protected static DB instance;

    IDatabaseDriver driver;

    public static DB getInstance() {
        return instance;
    }

    protected DB(IDatabaseDriver driver) {
        this.driver = driver;
    }

    public static void init(IDatabaseDriver driver){
        instance = new DB(driver);
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

    public ResultTable findAll(String resource) {
        return this.findAll("select * from %s".formatted(resource), new ArrayList<>());
    }
}
