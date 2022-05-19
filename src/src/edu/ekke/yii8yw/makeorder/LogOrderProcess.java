package edu.ekke.yii8yw.makeorder;

import com.mysql.cj.log.Log;
import edu.ekke.yii8yw.models.Order;
import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.models.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
public class LogOrderProcess implements MakeOrderObserver, DisplayOrder {

    private Order order;
    private User user;
    private ArrayList<Product> products;

    private MakeOrderSubject subject;

    public LogOrderProcess(MakeOrderSubject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void log() {
        Logger.getLogger(LogOrderProcess.class).info(products.toString());
        Logger.getLogger(LogOrderProcess.class).info("User order: %s".formatted(user.toString()));
        Logger.getLogger(LogOrderProcess.class).info("Order: %s".formatted(order.toString()));
    }

    @Override
    public void Update(ArrayList<Product> products, Order order, User user) {
        this.products = products;
        this.order = order;
        this.user = user;
        log();

    }
}
