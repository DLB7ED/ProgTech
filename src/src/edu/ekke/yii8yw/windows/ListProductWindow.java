package edu.ekke.yii8yw.windows;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.helpers.IListenToLambda;
import edu.ekke.yii8yw.helpers.List2;
import edu.ekke.yii8yw.helpers.SimpleDocumentListener;
import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.windows.components.states.AddNewState;
import edu.ekke.yii8yw.windows.components.states.ListWindowStateManager;
import edu.ekke.yii8yw.windows.components.states.UserState;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

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
    private ListWindowStateManager stateManager;
    public JList getList1() {
        return list1;
    }

    public ListProductWindow() throws HeadlessException {

        list1.addListSelectionListener(e -> handleSelection());

        refreshButton.addActionListener((event) -> populateList());

        addButton.addActionListener((event) -> {
            var newProduct = new Product();
            this.stateManager.setSelectedProduct(newProduct);
            this.stateManager.changeState(new AddNewState(this.stateManager));
        });

        this.stateManager = new ListWindowStateManager(this, Arrays.asList(leftButton, rightButton), Arrays.asList(txt_display, txt_price, txt_processor, txt_ram, txt_producer, txt_series, txt_storage, txt_storage_type));
        this.stateManager.changeState(new UserState(this.stateManager));

        setContentPane(mainPanel);
        setTitle("Laptop shop");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        populateList();
        initInputChangeEvents();
    }

    private void initInputChangeEvents() {
        listen(txt_producer, Product::setProducer);
        listen(txt_series, Product::setSeries);
        listen(txt_processor, Product::setProcessor);
        listen(txt_storage_type, Product::setStorageType);
        listen(txt_display, (product, text) -> product.setDisplay(Double.parseDouble(text)));
        listen(txt_ram, ((product, text) -> product.setRam(Integer.parseInt(text))));
        listen(txt_storage, ((product, text) -> product.setStorage(Integer.parseInt(text))));
        listen(txt_price, ((product, text) -> product.setPrice(Integer.parseInt(text))));
    }

    private void listen(JTextField textField, IListenToLambda lambda) {
        textField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            if(this.stateManager.getSelectedProduct() == null || Objects.equals(textField.getText(), "")) {

                Logger.getLogger(ListProductWindow.class).warn("Selected product or text field is null");
                return;
            }
            lambda.op(this.stateManager.getSelectedProduct(), textField.getText());
        });
    }

    public void clearInputs(){
        txt_producer.setText("");
        txt_series.setText("");
        txt_display.setText("");
        txt_processor.setText("");
        txt_ram.setText("");
        txt_storage_type.setText("");
        txt_storage.setText("");
        txt_price.setText("");
    }

    private void handleSelection() {
        Product selected = (Product) list1.getSelectedValue();
        this.stateManager.setSelectedProduct(selected);
        if (selected == null){
            Logger.getLogger(ListProductWindow.class).warn("Selected is null");
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
