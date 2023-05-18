package Controller;

import Component.Researcher;
import Model.ResearcherModel;
import Repository.ResearcherRepository;
import View.LoginView;
import View.ResearcherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final ResearcherRepository researcherRepository;
    private final LoginView loginView;

    public LoginController(ResearcherRepository researcherRepository, LoginView loginView) {
        this.researcherRepository = researcherRepository;
        this.loginView = loginView;

        this.loginView.addLoginListener(new LoginListener());
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Researcher researcher = researcherRepository.authenticate(username, password);

            if (researcher!=null) {
                JOptionPane.showMessageDialog(loginView, "Login successful");
                loginView.setVisible(false);
                ResearcherView profileView = new ResearcherView();
                ProfileController profileController = new ProfileController(researcher, profileView, researcherRepository);
                profileView.setVisible(true);

                profileController.displayProfilePage();

            }
            else {
                JOptionPane.showMessageDialog(loginView, "Invalid username or password");
            }
        }
    }
}
