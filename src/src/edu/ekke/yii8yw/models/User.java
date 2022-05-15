package edu.ekke.yii8yw.models;

import edu.ekke.yii8yw.core.database.DB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Model{

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    private boolean isAdmin = false;

    @Override
    public boolean save() {
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(this.getName());
            params.add(this.getEmail());
            params.add(this.getPassword());

            if (this.getId() == 0){
                DB.getInstance().execute("insert into users (name, email, password) values (?, ?, ?)", params);
                return true;
            }

            params.add(this.getId());
            DB.getInstance().execute("update users set name=?, email=?, password=? where id=?", params);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean load(long id) {
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(id);

            HashMap<String, Object> value = DB.getInstance().findOne("select * from users where id=?", params);

            this.fromHash(value);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean fromHash(HashMap<String, Object> map) {
        try {
            this.setId((long)map.get("id"));
            this.setName((String) map.get("name"));
            this.setEmail((String) map.get("email"));
            this.setPassword((String) map.get("password"));
            this.setIsAdmin((map.get("is_admin")) == "1");
            this.setCreatedAt((Timestamp) map.get("created_at"));

        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public HashMap<String, Object> toHash() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", this.getId());
        result.put("name", this.getName());
        result.put("email", this.getEmail());
        result.put("password", this.getPassword());
        result.put("is_admin", this.getIsAdmin() ? 1 : 0);
        result.put("created_at", this.getCreatedAt());

        return result;
    }
}
