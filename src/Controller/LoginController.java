package Controller;

import Component.Researcher;
import Model.ResearcherModel;
import View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private ResearcherModel researcherModel;
    private LoginView loginView;

    public LoginController(ResearcherModel researcherModel, LoginView loginView) {
        this.researcherModel = researcherModel;
        this.loginView = loginView;

        this.loginView.addLoginListener(new LoginListener());
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            boolean authenticated = researcherModel.authenticate(username, password);

            if (authenticated) {
                JOptionPane.showMessageDialog(loginView, "Login successful");
            } else {
                JOptionPane.showMessageDialog(loginView, "Invalid username or password");
            }
        }
    }
}
