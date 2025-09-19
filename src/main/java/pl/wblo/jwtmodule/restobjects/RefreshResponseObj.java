package pl.wblo.jwtmodule.restobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RefreshResponseObj implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Boolean mfaEnabled;
}
