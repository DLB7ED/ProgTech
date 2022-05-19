package edu.ekke.yii8yw.windows.components.states;

import edu.ekke.yii8yw.windows.UserLogin;

import java.awt.event.ActionEvent;

public class GuestState implements IListWindowState {

    private final ListWindowStateManager manager;

    public GuestState(ListWindowStateManager manager) {
        this.manager = manager;
    }

    @Override
    public void handleLeftButtonClick(ActionEvent event) {

    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {

    }

    @Override
    public void handleAuthButtonClick(ActionEvent event) {
        new UserLogin(() -> this.manager.changeState(new UserState(this.manager)));
    }

    @Override
    public boolean canEdit() {
        return false;
    }

    @Override
    public String leftButtonText() {
        return null;
    }

    @Override
    public String rightButtonText() {
        return null;
    }

    @Override
    public String authButtonText() {
        return "Login";
    }
}
