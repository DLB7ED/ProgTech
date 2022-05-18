package edu.ekke.yii8yw.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public abstract class Model {

    protected void setId(long id) {
        this.id = id;
    }

    protected void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    protected long id = 0;
    protected Timestamp createdAt;

    public long getId() {
        return id;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public abstract boolean save();
    public  abstract boolean load(long id);
    public  abstract boolean fromHash(HashMap<String, Object> hashMap);
    public  abstract  HashMap<String, Object> toHash();
}
