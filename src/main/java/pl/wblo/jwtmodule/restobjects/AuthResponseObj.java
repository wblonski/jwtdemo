package pl.wblo.jwtmodule.restobjects;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AuthResponseObj implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Boolean mfaEnabled;

    public AuthResponseObj() {}

    public AuthResponseObj(String accessToken, String refreshToken, Boolean mfaEnabled) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.mfaEnabled = mfaEnabled;
    }
}
