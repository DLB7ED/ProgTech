package edu.ekke.yii8yw.windows.components.states;

import edu.ekke.yii8yw.models.Product;
import edu.ekke.yii8yw.windows.ListProductWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ListWindowStateManager implements IListWindowState{
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

        this.leftButton.addActionListener(this::handleLeftButtonClick);
        this.rightButton.addActionListener(this::handleRightButtonClick);
        this.authButton.addActionListener(this::handleAuthButtonClick);

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

    @Override
    public void handleLeftButtonClick(ActionEvent event) {
        this.state.handleLeftButtonClick(event);
    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {
        this.state.handleRightButtonClick(event);
    }

    @Override
    public void handleAuthButtonClick(ActionEvent event) {
        this.state.handleAuthButtonClick(event);
    }

    @Override
    public boolean canEdit() {
        return this.state.canEdit();
    }

    @Override
    public String leftButtonText() {
        return this.state.leftButtonText();
    }

    @Override
    public String rightButtonText() {
        return this.state.rightButtonText();
    }

    @Override
    public String authButtonText() {
        return this.state.authButtonText();
    }

    public void changeState(IListWindowState newState) {
        this.state = newState;
        if(leftButtonText() != null) {
            this.leftButton.setText(leftButtonText());
            this.leftButton.setEnabled(true);
        } else {
            this.leftButton.setText("-");
            this.leftButton.setEnabled(false);
        }

        if(rightButtonText() != null) {
            this.rightButton.setText(rightButtonText());
            this.rightButton.setEnabled(true);
        } else {
            this.rightButton.setText("-");
            this.rightButton.setEnabled(false);
        }
        this.authButton.setText(this.authButtonText());

        this.addButton.setEnabled(canEdit());
        for (var input : inputs) {
            input.setEditable(canEdit());
        }
    }
}
