package edu.ekke.yii8yw.makeorder;

import java.util.Observer;

public interface MakeOrderSubject {
    void registerObserver(MakeOrderObserver observer);
    void removeObserver(MakeOrderObserver observer);
    void notifyObserver();
}
