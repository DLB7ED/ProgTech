package edu.ekke.yii8yw.makeorder;

import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.models.Product;

public interface SaveOrder {
    void save();
    void load();
    void removeProduct(Product product);
    void addProduct(Product product);
}
