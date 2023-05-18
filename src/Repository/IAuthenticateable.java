package Repository;

import Component.Researcher;

public interface IAuthenticateable {
    Researcher authenticate(String username, String password);
}
