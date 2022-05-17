package edu.ekke.yii8yw.windows;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.helpers.List2;
import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.windows.components.states.ListWindowState;
import edu.ekke.yii8yw.windows.components.states.UserState;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class ListProductWindow extends JFrame{
    private JPanel mainPanel;
    private JList list1;
    private JButton registerButton;
    private JButton refreshButton;
    private JButton logInButton;
    private JTextField txt_producer;
    private JButton leftButton;
    private JButton rightButton;
    private JButton addButton;
    private JTextField txt_series;
    private JTextField txt_display;
    private JTextField txt_processor;
    private JTextField txt_ram;
    private JTextField txt_storage_type;
    private JTextField txt_storage;
    private JTextField txt_price;
    private ListWindowState state;

    public ListProductWindow() throws HeadlessException {

        list1.addListSelectionListener(e -> handleSelection());

        refreshButton.addActionListener((event) -> populateList());

        this.state = new ListWindowState(this, Arrays.asList(leftButton, rightButton), Arrays.asList(txt_display, txt_price, txt_processor, txt_ram, txt_producer, txt_series, txt_storage, txt_storage_type));
        this.state.changeState(new UserState(this.state));

        setContentPane(mainPanel);
        setTitle("Laptop shop");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        populateList();
    }

    private void handleSelection() {
        Product selected = (Product) list1.getSelectedValue();
        this.state.setSelectedProduct(selected);
        if (selected == null){
            return;
        }
        txt_producer.setText(selected.getProducer());
        txt_series.setText(selected.getSeries());
        txt_display.setText(String.valueOf(selected.getDisplay()));
        txt_processor.setText(selected.getProcessor());
        txt_ram.setText(String.valueOf(selected.getRam()));
        txt_storage_type.setText(selected.getStorageType());
        txt_storage.setText(String.valueOf(selected.getStorage()));
        txt_price.setText(String.valueOf(selected.getPrice()));
    }

    public void populateList() {
        DefaultListModel<Product> products = new DefaultListModel<>();
        List2<HashMap<String, Object>> hashMaps = DB.getInstance().findAll("products").all();
        for (HashMap<String, Object> res: hashMaps) {
            Product result = new Product();
            result.fromHash(res);
            products.addElement(result);
        }
        list1.setModel(products);
    }

}
