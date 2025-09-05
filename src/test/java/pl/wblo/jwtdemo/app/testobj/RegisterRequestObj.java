package pl.wblo.jwtdemo.app.testobj;

import lombok.Data;
import pl.wblo.jwtdemo.app.user.Role;
import pl.wblo.jwtdemo.app.user.User;

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

    public static RegisterRequestObj getUserDto(User user) {
        return new RegisterRequestObj(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getRole(),
                user.isMfaEnabled());
    }
}
