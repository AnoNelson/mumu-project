package gov.rw.brd.domain;

import javax.validation.constraints.NotBlank;

/**
 * Created by Nick on 1/16/2020.
 */

public class LoginRequest {
    @NotBlank(message = "username must not be blank")
    private String userName;
    @NotBlank(message = "password must not be blank")
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
