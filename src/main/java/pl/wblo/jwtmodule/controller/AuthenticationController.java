package pl.wblo.jwtmodule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wblo.jwtmodule.restobjects.AuthRequestObj;
import pl.wblo.jwtmodule.restobjects.AuthResponseObj;
import pl.wblo.jwtmodule.restobjects.RegisterRequestObj;
import pl.wblo.jwtmodule.restobjects.VerifyRequestObj;
import pl.wblo.jwtmodule.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestObj request) throws Exception {
        service.register(request);
//        if (request.isMfaEnabled()) {
//            return ResponseEntity.ok();
//        }
//        return ResponseEntity.accepted().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseObj> authenticate(
            @RequestBody AuthRequestObj request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(
            @RequestBody VerifyRequestObj verificationRequest
    ) {
        return ResponseEntity.ok(service.verifyCode(verificationRequest));
    }


}
