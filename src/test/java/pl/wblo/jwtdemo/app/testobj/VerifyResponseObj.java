package pl.wblo.jwtdemo.app.testobj;

import lombok.Data;

@Data
public class VerifyResponseObj {
    private String accessToken;
    private Boolean mfaEnabled;

    public VerifyResponseObj() {
    }

    public VerifyResponseObj(String accessToken, Boolean mfaEnabled) {
        this.accessToken = accessToken;
        this.mfaEnabled = mfaEnabled;
    }
}
