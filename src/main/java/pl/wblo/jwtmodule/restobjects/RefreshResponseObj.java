package pl.wblo.jwtmodule.restobjects;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RefreshResponseObj implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Boolean mfaEnabled;

    public RefreshResponseObj() {}

    public RefreshResponseObj(String accessToken, String refreshToken, Boolean mfaEnabled) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.mfaEnabled = mfaEnabled;
    }
}
