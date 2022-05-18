package edu.ekke.yii8yw.windows.components.states;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.helpers.Tools;

import java.awt.event.ActionEvent;

public class AdminState implements IListWindowState{
    private final ListWindowStateManager manager;

    public AdminState(ListWindowStateManager parent) {
        this.manager = parent;
    }

    @Override
    public void handleLeftButtonClick(ActionEvent event) {
        long productId = this.manager.getSelectedProduct().getId();
        DB.getInstance().execute("delete from products where id=?", Tools.asList(productId));
        this.manager.getMainWindow().populateList();
        this.manager.changeState(new UserState(this.manager));
        this.manager.getMainWindow().clearInputs();
    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {
        this.manager.getSelectedProduct().save();
        this.manager.getMainWindow().populateList();
        this.manager.changeState(new UserState(this.manager));
        this.manager.getMainWindow().clearInputs();
    }

    @Override
    public boolean canEdit() {
        return true;
    }

    @Override
    public String leftButtonText() {
        return "Delete";
    }

    @Override
    public String rightButtonText() {
        return "Save";
    }
}
