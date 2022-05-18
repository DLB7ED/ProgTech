package edu.ekke.yii8yw.windows.components.states;

import java.awt.event.ActionEvent;

public class UserState implements IListWindowState{

    private final ListWindowStateManager manager;

    public UserState(ListWindowStateManager manager) {
        this.manager = manager;
    }
    @Override
    public void handleLeftButtonClick(ActionEvent event) {

    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {
        manager.changeState(new AdminState(manager));
    }

    @Override
    public boolean canEdit() {
        return false;
    }

    @Override
    public String leftButtonText() {
        return "Order";
    }

    @Override
    public String rightButtonText() {
        return "Edit";
    }
}
