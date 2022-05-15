package edu.ekke.yii8yw.core.database;

import edu.ekke.yii8yw.core.Config;
import edu.ekke.yii8yw.helpers.List2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MySqlDriver implements IDatabaseDriver{

    Connection activeConnection;

    public MySqlDriver(Connection activeConnection) {
        this.activeConnection = activeConnection;
    }

    public MySqlDriver() {
        Config instance = Config.getInstance();
        try {
            this.activeConnection = DriverManager.getConnection("jdbc:" + instance.getDbConnectionURL(), instance.getDbUser(), instance.getDbPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Object> findOne(String query, ArrayList<Object> args) {
        return findAll(query, args).first();
    }

    @Override
    public ResultTable findAll(String query, ArrayList<Object> args) {
        ResultTable resultTable = new ResultTable();

        try {
            PreparedStatement stmt = this.activeConnection.prepareStatement(query);
            for (int i = 0; i < args.size(); i++) {
                stmt.setObject(i+1, args.get(i));
            }
            ResultSet rs =  stmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            for (int i = 1; i < colCount + 1; i++) {
                resultTable.addField(meta.getColumnLabel(i));
            }

            while (rs.next()){
                List2<Object> list = new List2<>();
                for (int i = 1; i < colCount + 1; i++) {
                    list.add(rs.getObject(i));
                }
                resultTable.add(list);
            }

            rs.close();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return resultTable;
    }

    @Override
    public int execute(String query, ArrayList<Object> args) {
        int result = -1;
        try {
            PreparedStatement stmt = this.activeConnection.prepareStatement(query);
            for (int i = 0; i < args.size(); i++) {
                stmt.setObject(i+1, args.get(i));
            }

            result = stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
