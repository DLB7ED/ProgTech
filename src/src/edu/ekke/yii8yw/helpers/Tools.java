package edu.ekke.yii8yw.helpers;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    public static ArrayList<Object> asList(Object ...args){
        return new ArrayList<>(List.of(args));
    }
}
