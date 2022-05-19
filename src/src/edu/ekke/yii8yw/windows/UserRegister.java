package edu.ekke.yii8yw.windows;

import edu.ekke.yii8yw.core.Auth;
import edu.ekke.yii8yw.core.exceptions.DatabaseException;
import edu.ekke.yii8yw.core.exceptions.ValidatorException;

import javax.swing.*;
import java.awt.event.*;

public class UserRegister extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField txt_password_confirmation;
    private JPasswordField txt_password;
    private JTextField txt_email;
    private JTextField txt_name;

    public UserRegister() {
        setContentPane(contentPane);
        setTitle("Register");
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        try {
            Auth.register(txt_name.getText(), txt_email.getText(), new String(txt_password_confirmation.getPassword()), new String(txt_password.getPassword()));
        } catch (ValidatorException | DatabaseException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
