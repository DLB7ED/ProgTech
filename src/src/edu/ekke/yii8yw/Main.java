package edu.ekke.yii8yw;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.core.database.MySqlDriver;
import edu.ekke.yii8yw.windows.ListProductWindow;

public class Main {

    private static void init(){
        DB.init(new MySqlDriver());
    }
    public static void main(String[] args) {
        init();
        ListProductWindow window2 = new ListProductWindow();
    }
}
