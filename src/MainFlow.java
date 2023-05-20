import Controller.LoginController;
import FileOperations.BibOperations.ReadBibFile;
import Interface.ResearcherRepository;
import FileOperations.XmlOperations.XmlResearcherOperations;
import View.LoginView;

public class MainFlow {
    public void run() {
        ReadBibFile readBibFile = new ReadBibFile();
        readBibFile.read();

        ResearcherRepository researcherRepository = new XmlResearcherOperations();
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(researcherRepository, loginView);
        loginView.setVisible(true);
    }
}
