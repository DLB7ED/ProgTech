package edu.ekke.yii8yw.makeorder;


import edu.ekke.yii8yw.models.Order;
import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.models.User;

import java.util.ArrayList;

public class MakeOrder implements MakeOrderSubject, SaveOrder {

    private Order order;
    private User user;
    private ArrayList<Product> products;

    ArrayList observers;
    public MakeOrder(Builder builder){
        this.order = builder.order;
        this.user = builder.user;
        this.products = builder.products;
        notifyObserver();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public void registerObserver(MakeOrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MakeOrderObserver observer) {
        int i = observers.indexOf(observer);
        if(i >= 0) observers.remove(i);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); ++i){
            MakeOrderObserver observer = (MakeOrderObserver) observers.get(i);
            observer.Update(products, order, user);
        }
    }

    @Override
    public void save() {
        //todo
    }

    @Override
    public void load() {
        //todo
    }

    public static class Builder{
        public Builder(){}
        private Order order;
        private User user;
        private ArrayList<Product> products;

        public Builder order(Order order){
            products = new ArrayList<Product>();
            this.order = order;
            return this;
        }
        public Builder user(User user){
            this.user = user;
            return this;
        }
        public Builder product(Product product){
            this.products.add(product);
            return this;
        }
        public MakeOrder build(){
            MakeOrder makeOrder = new MakeOrder(this);
            return makeOrder;
        }
    }
}
