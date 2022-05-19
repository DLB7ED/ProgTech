package edu.ekke.yii8yw.makeorder;

import edu.ekke.yii8yw.models.Order;
import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.models.User;
import edu.ekke.yii8yw.windows.MakeNewOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MakeOrderTest {
    private final Product product = new Product.Builder("HP")
            .display(2.0).ram(8).price(10000).processor("intel").series("Mega").storage(500).build();
    private final User user = new User.Builder("Peti")
            .email("kbt@gmail.com").password("kolbász").isAdmin(true).build();
    private final Order order = new Order.Builder("Mezőkövesd")
            .street("Főút").houseNumber(1).phone("0620/111-1111").build();

    @Test
    void getOrder() {
        MakeOrder testData = new MakeOrder.Builder().order(order).product(product).user(user).build();
        Order testOrderData = testData.getOrder();
        Assertions.assertEquals(order, testOrderData);
    }

    @Test
    void setOrder() {
        final Order testOrder = new Order.Builder("Jászkisér")
                .street("Főút").houseNumber(1).phone("0620/111-1111").build();
        MakeOrder testData = new MakeOrder.Builder().order(order).product(product).user(user).build();
        testData.setOrder(testOrder);
        Order testOrderData = testData.getOrder();
        Assertions.assertEquals(testOrder, testOrderData);

    }

    @Test
    void getUser() {
        MakeOrder testData = new MakeOrder.Builder().order(order).product(product).user(user).build();
        User testUserData = testData.getUser();
        Assertions.assertEquals(user, testUserData);
    }

    @Test
    void setUser() {
        final User testUser = new User.Builder("Ádám")
                .email("adam@gmail.com").password("kolbász").isAdmin(true).build();
        MakeOrder testData = new MakeOrder.Builder().order(order).product(product).user(user).build();
        testData.setUser(testUser);
        Order testOrderData = testData.getOrder();
        Assertions.assertEquals(testUser, testOrderData);
    }

    @Test
    void getProducts() {
        MakeOrder testData = new MakeOrder.Builder().order(order).product(product).user(user).build();
        ArrayList<Product> testProductData = testData.getProducts();
        Assertions.assertTrue(testProductData.contains(product));
    }

    @Test
    void setProducts() {
        final Product testProduct = new Product.Builder("HP")
                .display(2.0).ram(8).price(10000).processor("AMD").series("Mega").storage(1000).build();
        MakeOrder testData = new MakeOrder.Builder().order(order).product(product).user(user).build();
        testData.addProduct(testProduct);
        ArrayList<Product> testProductData = testData.getProducts();
        Assertions.assertTrue(testProductData.contains(testProduct));
    }

    @Test
    void save() {
        //todo
    }

    @Test
    void load() {
        //todo
    }
}