package pl.wblo.jwtmodule.restobjects;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class VerifyResponseObj implements Serializable {
    private String accessToken;
    private Boolean mfaEnabled;

    public VerifyResponseObj() {
    }

    public VerifyResponseObj(String accessToken, Boolean mfaEnabled) {
        this.accessToken = accessToken;
        this.mfaEnabled = mfaEnabled;
    }
}
