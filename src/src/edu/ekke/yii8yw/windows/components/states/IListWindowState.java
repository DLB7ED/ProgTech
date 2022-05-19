package edu.ekke.yii8yw.windows.components.states;

import java.awt.event.ActionEvent;

public interface IListWindowState {
    void handleLeftButtonClick(ActionEvent event);
    void handleRightButtonClick(ActionEvent event);
    void handleAuthButtonClick(ActionEvent event);
    boolean canEdit();
    String leftButtonText();
    String rightButtonText();

    String authButtonText();
}
