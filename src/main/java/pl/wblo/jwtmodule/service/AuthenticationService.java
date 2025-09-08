package pl.wblo.jwtmodule.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wblo.appmodule.repository.UserRepository;
import pl.wblo.appmodule.repository.entity.User;
import pl.wblo.jwtmodule.restobjects.*;

import java.io.IOException;

@Data
@Builder
@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TwoFactorAuthenticationService tfaService;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, TwoFactorAuthenticationService tfaService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tfaService = tfaService;
    }

    public void register(RegisterRequestObj request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .mfaEnabled(request.isMfaEnabled())
                .secret("")
                .build();

        // if MFA enabled --> Generate Secret
//        if (request.isMfaEnabled()) {
//            user.setSecret(tfaService.generateNewSecret());
//        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
//        return AuthenticationResponse.builder()
//                .secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .mfaEnabled(user.isMfaEnabled())
//                .build();
    }

    public AuthResponseObj authenticate(AuthRequestObj request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
                );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if (user.isMfaEnabled()) {
            return AuthResponseObj.builder()
                    .accessToken("")
                    .refreshToken("")
                    .mfaEnabled(true)
                    .build();
        }
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponseObj.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .mfaEnabled(false)
                .build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository
                    .findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                var authResponse = RefreshResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .mfaEnabled(false)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public VerifyResponseObj verifyCode(VerifyRequestObj verifyRequestObj) {
        UserDetails userDetails = repository
                .findByEmail(verifyRequestObj.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user found with %S", verifyRequestObj.getEmail()))
                );
//        if (tfaService.isOtpNotValid(user.getSecret(), verifyRequestObj.getCode())) {
//            throw new BadCredentialsException("Code is not correct");
//        }
        var jwtToken = jwtService.generateToken(userDetails);
        return VerifyResponseObj.builder()
                .accessToken(jwtToken)
                .mfaEnabled(userDetails.isEnabled())
                .build();
    }

}
