package natamobile.natamobile2.Models;

import java.io.Serializable;

/**
 * Created by adres on 06-11-17.
 */

public class UseraccountCredentials implements Serializable {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}