package Model;

import helper.ResearcherAuthenticator;

public class LoginModel {
    private String username;
    private String password;
    private ResearcherAuthenticator authenticator;

    public boolean authenticate(String username, String password, ResearcherAuthenticator authenticator) {
        // Simulate authentication logic
        return username.equals("admin") && password.equals("password");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
