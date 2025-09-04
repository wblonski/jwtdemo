package pl.wblo.jwtdemo.app.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequest {

    private String email;
    private String code;
}
