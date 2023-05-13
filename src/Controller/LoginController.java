package Controller;

import Model.LoginModel;
import View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private LoginModel model;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        this.view.addSubmitListener(new SubmitListener());
        this.view.addResetListener(new ResetListener());
    }

    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setUsername(view.getUsername());
            model.setPassword(view.getPassword());
            if (model.authenticate(model.getUsername(), model.getPassword())) {
                view.showError("Login Successful!");
                view.clearFields();
            } else {
                view.showError("Invalid Username or Password");
            }
        }
    }
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.clearFields();
        }
    }
}
