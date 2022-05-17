package edu.ekke.yii8yw.windows.components.states;

import java.awt.event.ActionEvent;

public class UserState implements IListWindowState{

    private final ListWindowState parent;

    public UserState(ListWindowState parent) {
        this.parent = parent;
    }
    @Override
    public void handleLeftButtonClick(ActionEvent event) {

    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {
        parent.changeState(new AdminState(parent));
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
