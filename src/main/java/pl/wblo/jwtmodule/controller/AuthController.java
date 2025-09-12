package pl.wblo.jwtmodule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wblo.jwtmodule.restobjects.*;
import pl.wblo.jwtmodule.util.MyLogger;
import pl.wblo.jwtmodule.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestObj request) {
        // TODO wblo: zrób blokadę dubletów użytkownika - musi mieć unikalny email
        // TODO wblo: Jwt sprawdza po emailu, więc email musi być unikalny
        MyLogger.debug("/register");
        service.register(request);
//        if (request.isMfaEnabled()) {
//            return ResponseEntity.ok(response);
//        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseObj> authenticate(
            @RequestBody AuthRequestObj request
    ) {
        MyLogger.debug("authenticate");
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        MyLogger.debug("/refresh-token");
        service.refreshToken(request, response);
    }

    @PostMapping("/verify")
    public ResponseEntity<VerifyResponseObj> verifyCode(
            @RequestBody VerifyRequestObj verifyRequestObj
    ) {
        MyLogger.debug("/verify");
        return ResponseEntity.ok(service.verifyCode(verifyRequestObj));
    }


}
