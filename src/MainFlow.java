import Controller.LoginController;
import Model.LoginModel;
import Reader.ReadFile;
import View.LoginView;

public class MainFlow {
    public void run() {
        ReadFile readFile = new ReadFile();
        readFile.read();

        LoginView loginView = new View.LoginView();
        LoginModel model = new LoginModel();
        LoginController controller = new LoginController(loginView, model);

        // Display the view
        loginView.setVisible(true);

    }
}
