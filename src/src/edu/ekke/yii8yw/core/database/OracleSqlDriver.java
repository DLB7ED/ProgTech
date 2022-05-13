package edu.ekke.yii8yw.core.database;

import java.util.ArrayList;
import java.util.HashMap;

public class OracleSqlDriver implements  IDatabaseDriver{
    @Override
    public HashMap<String, Object> findOne(String query, ArrayList<Object> args) {
        return null;
    }

    @Override
    public ResultTable findAll(String query, ArrayList<Object> args) {
        return null;
    }

    @Override
    public int execute(String query, ArrayList<Object> args) {
        return 0;
    }
}
