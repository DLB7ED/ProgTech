package edu.ekke.yii8yw.windows;

import edu.ekke.yii8yw.core.Auth;
import edu.ekke.yii8yw.core.exceptions.ValidatorException;

import javax.swing.*;
import java.awt.event.*;

public class UserLogin extends JDialog {
    private Runnable callback;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField txt_password;
    private JTextField txt_email;

    public UserLogin() {
        setContentPane(contentPane);
        setTitle("Login");
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public UserLogin(Runnable callback) {
        this();
        this.callback = callback;
    }

    private void onOK() {
        // add your code here
        try {
            Auth.login(txt_email.getText(), new String(txt_password.getPassword()));
            if(this.callback != null)
                this.callback.run();
        } catch (ValidatorException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
