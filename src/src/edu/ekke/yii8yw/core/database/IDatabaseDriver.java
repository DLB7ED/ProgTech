package edu.ekke.yii8yw.core.database;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabaseDriver {
    public HashMap<String, Object> findOne(String query, ArrayList<Object> args);
    public ResultTable findAll(String query, ArrayList<Object> args);
    public int execute(String query, ArrayList<Object> args);
}
