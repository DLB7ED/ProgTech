package edu.ekke.yii8yw.models;

import edu.ekke.yii8yw.core.database.DB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Order extends Model{

    private String city;
    private String street;
    private int houseNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;


    @Override
    public boolean save() {
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(this.getCity());
            params.add(this.getStreet());
            params.add(this.getHouseNumber());
            params.add(this.getPhone());

            if (this.getId() == 0){
                DB.getInstance().execute("insert into orders (city, street, house_number, phone) values " +
                        "(?, ?, ?, ?, ?) where id=?", params);
                return  true;
            }

            params.add(this.getId());
            DB.getInstance().execute("update orders set city=?, street=?, house_number=?, phone=?", params);
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

            HashMap<String, Object> value = DB.getInstance().findOne("select * from orders where id=?", params);
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
            this.setId((long) map.get("id"));
            this.setCity((String) map.get("city"));
            this.setStreet((String) map.get("street"));
            this.setHouseNumber((int) map.get("house_number"));
            this.setPhone((String) map.get("phone"));
            this.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)map.get("created_at")));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public HashMap<String, Object> toHash() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", this.getId());
        result.put("city", this.getCity());
        result.put("street", this.getStreet());
        result.put("house_number", this.getHouseNumber());
        result.put("phone", this.getPhone());
        result.put("created_at", this.getCreatedAt().toString());

        return result;
    }
}
