package Controller;

import Component.Researcher;
import Model.ResearcherModel;
import View.LoginView;
import View.ResearcherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final ResearcherModel researcherModel;
    private final LoginView loginView;

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
                Researcher researcher = researcherModel.getResearcher(username);
                ResearcherView profileView = new ResearcherView();
                ProfileController profileController = new ProfileController(researcher, profileView);
                profileController.displayProfilePage();

            }
            else {
                JOptionPane.showMessageDialog(loginView, "Invalid username or password");
            }
        }
    }
}
