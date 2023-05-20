package Interface;

import Model.Researcher;

public interface IAuthenticateable {
    Researcher authenticate(String username, String password);
}
