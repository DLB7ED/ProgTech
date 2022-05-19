package edu.ekke.yii8yw.windows.components.states;

import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.windows.ListProductWindow;

import javax.swing.*;
import java.util.List;

public class ListWindowStateManager {
    private final JButton leftButton;
    private final JButton rightButton;
    private final List<JTextField> inputs;
    private final JButton authButton;
    private final JButton addButton;
    private IListWindowState state;
    private Product selectedProduct;
    private final ListProductWindow listWindow;

    public ListWindowStateManager(ListProductWindow parent, List<JButton> buttons, List<JTextField> inputs) {
        this.listWindow = parent;
        this.leftButton = buttons.get(0);
        this.rightButton = buttons.get(1);
        this.authButton = buttons.get(2);
        this.addButton = buttons.get(3);
        this.inputs = inputs;

        this.leftButton.addActionListener((e) -> this.state.handleLeftButtonClick(e));
        this.rightButton.addActionListener((e) -> this.state.handleRightButtonClick(e));
        this.authButton.addActionListener((e) -> this.state.handleAuthButtonClick(e));

    }


    public ListProductWindow getMainWindow() {
        return listWindow;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void changeState(IListWindowState newState) {
        this.state = newState;
        if(this.state.leftButtonText() != null) {
            this.leftButton.setText(this.state.leftButtonText());
            this.leftButton.setEnabled(true);
        } else {
            this.leftButton.setText("-");
            this.leftButton.setEnabled(false);
        }

        if(this.state.rightButtonText() != null) {
            this.rightButton.setText(this.state.rightButtonText());
            this.rightButton.setEnabled(true);
        } else {
            this.rightButton.setText("-");
            this.rightButton.setEnabled(false);
        }
        this.authButton.setText(this.state.authButtonText());

        this.addButton.setEnabled(this.state.canEdit());
        for (var input : inputs) {
            input.setEditable(this.state.canEdit());
        }
    }
}
