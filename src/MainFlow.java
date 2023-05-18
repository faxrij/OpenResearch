import Controller.LoginController;
import Model.ResearcherModel;
import Reader.ReadFile;
import Repository.ResearcherRepository;
import Repository.XmlResearcherRepository;
import View.LoginView;
import View.ResearcherView;

public class MainFlow {
    public void run() {
        ReadFile readFile = new ReadFile();
        readFile.read();

        ResearcherRepository researcherRepository = new XmlResearcherRepository("researchers.xml");
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(researcherRepository, loginView);
        loginView.setVisible(true);

    }
}
