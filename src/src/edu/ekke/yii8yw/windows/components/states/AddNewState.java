package edu.ekke.yii8yw.windows.components.states;

import java.awt.event.ActionEvent;

public class AddNewState implements IListWindowState{

    private final ListWindowStateManager manager;

    public AddNewState(ListWindowStateManager manager) {
        this.manager = manager;
        this.manager.getMainWindow().clearInputs();
        this.manager.getMainWindow().getList1().setEnabled(false);
    }

    @Override
    public void handleLeftButtonClick(ActionEvent event) {
        this.manager.setSelectedProduct(null);
        this.manager.changeState(new UserState(this.manager));
        this.manager.getMainWindow().getList1().setEnabled(true);
        this.manager.getMainWindow().clearInputs();
    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {
        this.manager.getSelectedProduct().save();
        this.manager.getMainWindow().populateList();
        this.manager.changeState(new UserState(this.manager));
        this.manager.getMainWindow().getList1().setEnabled(true);
    }

    @Override
    public boolean canEdit() {
        return true;
    }

    @Override
    public String leftButtonText() {
        return "Cancel";
    }

    @Override
    public String rightButtonText() {
        return "Save";
    }
}
