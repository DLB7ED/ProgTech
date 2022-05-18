package edu.ekke.yii8yw.windows.components.states;

import java.awt.event.ActionEvent;

public interface IListWindowState {
    public void handleLeftButtonClick(ActionEvent event);
    public void handleRightButtonClick(ActionEvent event);
    public boolean canEdit();
    public String leftButtonText();
    public  String rightButtonText();
}
