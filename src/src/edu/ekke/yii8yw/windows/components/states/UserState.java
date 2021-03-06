package edu.ekke.yii8yw.windows.components.states;

import edu.ekke.yii8yw.core.Auth;

import javax.swing.*;
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
        if(!Auth.isAdmin()) {
            JOptionPane.showMessageDialog(this.manager.getMainWindow(),
                    "Only admins can edit products",
                    "Forbidden",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        manager.changeState(new EditState(manager));
    }

    @Override
    public void handleAuthButtonClick(ActionEvent event) {
        Auth.logout();
        this.manager.changeState(new GuestState(manager));
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

    @Override
    public String authButtonText() {
        return "Logout";
    }
}
