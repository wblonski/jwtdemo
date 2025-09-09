package pl.wblo.jwtmodule.restobjects;

import lombok.Data;
import pl.wblo.jwtmodule.security.config.Role;

import java.io.Serializable;

@Data
public class RegisterRequestObj implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private boolean mfaEnabled;

    public RegisterRequestObj() {
    }

    public RegisterRequestObj(String firstname, String lastname, String email, String password, Role role, boolean mfaEnabled) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.mfaEnabled = mfaEnabled;
    }
}
