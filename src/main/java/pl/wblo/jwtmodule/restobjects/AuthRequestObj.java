package pl.wblo.jwtmodule.restobjects;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequestObj implements Serializable {
    private String email;
    private String password;

    public AuthRequestObj() {}

    public AuthRequestObj(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
