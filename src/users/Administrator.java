package src.users;

import java.io.Serializable;

public class Administrator implements Serializable {
    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void ChangeCredintials(String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
