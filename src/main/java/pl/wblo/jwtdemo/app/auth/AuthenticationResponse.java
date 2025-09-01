package pl.wblo.jwtdemo.app.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse implements Serializable {

  private String accessToken;
  private String refreshToken;
  private boolean mfaEnabled;
  private String secretImageUri;


}
