package edu.ekke.yii8yw.windows.components.states;

import edu.ekke.yii8yw.core.database.DB;
import edu.ekke.yii8yw.helpers.Tools;

import java.awt.event.ActionEvent;

public class AdminState implements IListWindowState{
    private final ListWindowState parent;

    public AdminState(ListWindowState parent) {
        this.parent = parent;
    }

    @Override
    public void handleLeftButtonClick(ActionEvent event) {
        long productId = this.parent.getSelectedProduct().getId();
        DB.getInstance().execute("delete from products where id=?", Tools.asList(productId));
        this.parent.getListWindow().populateList();
    }

    @Override
    public void handleRightButtonClick(ActionEvent event) {

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
