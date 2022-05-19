package edu.ekke.yii8yw.models;

import edu.ekke.yii8yw.core.database.DB;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Product extends Model {

    private String producer;
    private String series;
    private double display;
    private String processor;
    private int ram;
    private String storageType;
    private int storage;
    private int price;

    public Product(){}

    public Product(Builder builder) {
        this.producer = builder.producer;
        this.series = builder.series;
        this.display = builder.display;
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.storageType = builder.storageType;
        this.storage = builder.storage;
        this.price = builder.price;
    }
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

            if (this.getId() == 0) {
                DB.getInstance().execute("insert into products " +
                        "(producer, series, display, processor, RAM, storage_type, storage, price) values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?)", params);
                Logger.getLogger(Product.class).info("Inserted product: %s %s".formatted(getProducer(), getSeries()));
                return true;
            }

            params.add(this.getId());
            DB.getInstance().execute("update products set producer=?, series=?, display=?, " +
                    "processor=?, RAM=?, storage_type=?, storage=?, price=? where id=?", params);
            Logger.getLogger(Product.class).info("Updated product with id: %d".formatted(getId()));
            return true;
        } catch (Exception e) {
            Logger.getLogger(Product.class).error("Save failed");
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

            Logger.getLogger(Product.class).info("Loaded product with id: %d".formatted(this.getId()));
            return true;
        } catch (Exception e) {
            Logger.getLogger(Product.class).error("Load failed");
            return false;
        }
    }

    @Override
    public boolean fromHash(HashMap<String, Object> map) {
        try {
            this.setId((long) map.get("id"));
            this.setProducer((String) map.get("producer"));
            this.setSeries((String) map.get("series"));
            this.setDisplay((double) map.get("display"));
            this.setProcessor((String) map.get("processor"));
            this.setRam((int) map.get("RAM"));
            this.setStorageType((String) map.get("storage_type"));
            this.setStorage((int) map.get("storage"));
            this.setPrice((int) map.get("price"));
            this.setCreatedAt((Timestamp) map.get("created_at"));
        } catch (Exception e) {
            Logger.getLogger(Product.class).error("FromHash failed: %s".formatted(map.toString()));
            return false;
        }
        Logger.getLogger(Product.class).info("Loaded from Hash");
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

        Logger.getLogger(Product.class).info("Converted to hash: %s %s".formatted(getProducer(), getSeries()));
        return result;
    }

    @Override
    public String toString() {
        return "%s %s (%d Ft)".formatted(this.producer, this.series, this.price);
    }

    public static class Builder {
        private String producer;
        private String series;
        private double display;
        private String processor;
        private int ram;
        private String storageType;
        private int storage;
        private int price;

        public Builder(String producer) {
            this.producer = producer;
        }

        public Builder series(String series) {
            this.series = series;
            return this;
        }
        public Builder display(double display){
            this.display = display;
            return this;
        }
        public Builder processor(String processor){
            this.processor = processor;
            return this;
        }
        public Builder ram(int ram){
            this.ram = ram;
            return this;
        }
        public Builder storageType(String storageType){
            this.storageType = storageType;
            return this;
        }
        public Builder storage(int storage){
            this.storage = storage;
            return this;
        }
        public Builder price(int price){
            this.price = price;
            return this;
        }

        public Product build(){
            Product product = new Product(this);
            return product;
        }
    }
}
