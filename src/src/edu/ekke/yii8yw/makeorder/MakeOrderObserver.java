package edu.ekke.yii8yw.makeorder;

import edu.ekke.yii8yw.models.Order;
import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.models.User;

import java.util.ArrayList;

public interface MakeOrderObserver {
    void Update(ArrayList<Product> products, Order order, User user);
}
