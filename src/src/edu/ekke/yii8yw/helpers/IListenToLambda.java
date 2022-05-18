package edu.ekke.yii8yw.helpers;

import edu.ekke.yii8yw.models.Product;

@FunctionalInterface
public interface IListenToLambda {
    void op(Product product, String text);
}
