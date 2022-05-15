package edu.ekke.yii8yw.models;

import edu.ekke.yii8yw.core.database.DB;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Product extends Model{

    private String producer;
    private String series;
    private double display;
    private String processor;
    private int ram;
    private String storageType;
    private int storage;
    private int price;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public double getDisplay() {
        return display;
    }

    public void setDisplay(double display) {
        this.display = display;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean save() {
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(this.getProducer());
            params.add(this.getSeries());
            params.add(this.getDisplay());
            params.add(this.getProcessor());
            params.add(this.getRam());
            params.add(this.getStorageType());
            params.add(this.getStorage());
            params.add(this.getPrice());

            if (this.getId() == 0){
                DB.getInstance().execute("insert into products " +
                        "(producer, series, display, processor, RAM, storage_type, storage, price) values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?)", params);
                return true;
            }

            params.add(this.getId());
            DB.getInstance().execute("update products set producer=?, series=?, display=?, " +
                    "processor=?, RAM=?, storage_type=?, storage=?, price=? where id=?", params);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean load(long id) {
        try {
            ArrayList<Object> params = new ArrayList<>();
            params.add(id);

            HashMap<String, Object> value = DB.getInstance().findOne("select * from products where id=?", params);
            this.fromHash(value);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean fromHash(HashMap<String, Object> map) {
        try {
            this.setProducer((String) map.get("producer"));
            this.setSeries((String) map.get("series"));
            this.setDisplay((double) map.get("display"));
            this.setProcessor((String) map.get("processor"));
            this.setRam((int) map.get("RAM"));
            this.setStorageType((String) map.get("storage_type"));
            this.setStorage((int) map.get("storage"));
            this.setPrice((int) map.get("price"));
            this.setCreatedAt((Timestamp) map.get("created_at"));
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
        result.put("producer", this.getProducer());
        result.put("series", this.getSeries());
        result.put("display", this.getDisplay());
        result.put("processor", this.getProcessor());
        result.put("RAM", this.getRam());
        result.put("storage_type", this.getStorageType());
        result.put("storage", this.getStorage());
        result.put("price", this.getPrice());
        result.put("created_at", this.getCreatedAt());

        return result;
    }
}
