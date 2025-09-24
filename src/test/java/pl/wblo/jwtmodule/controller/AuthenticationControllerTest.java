package pl.wblo.jwtmodule.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wblo.jwtmodule.IntegrationTest;
import pl.wblo.jwtmodule.restobjects.*;
import pl.wblo.jwtmodule.security.config.Role;
import pl.wblo.jwtmodule.util.MyLogger;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

class AuthenticationControllerTest implements IntegrationTest {
    final String registerUrlStr = "http://localhost:8080/api/v1/auth/register";
    final String refreshTokenUrlStr = "http://localhost:8080/api/v1/auth/refresh-token";
    final String authTokenUrlStr = "http://localhost:8080/api/v1/auth/authenticate";
    final String verifyUrlStr = "http://localhost:8080/api/v1/auth/verify";
    private HttpRequest request;
    private HttpResponse<byte[]> response;
    private ErrorResponseObj errRespData;
    private AuthResponseObj authRespData;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

    @Test
    void returns200_whenUserRegisterWorks() {
        // Expected empty response = 200, else ErrorResponseObj

        final String firstName = UUID.randomUUID().toString();
        final String lastName = UUID.randomUUID().toString();
        final String email = "%s.%s@domain.com".formatted(firstName, lastName);
        final RegisterRequestObj registerUser = new RegisterRequestObj(firstName, lastName, email, "password", Role.ADMIN, false);

        try {
            request = RequestFactory.getUserRegisterRequest(registerUrlStr, registerUser);
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.body().length != 0) {
                // wystąpił błąd
                errRespData = objectMapper.readValue(response.body(), ErrorResponseObj.class);
                Assertions.fail("Error at timestamp=%s, status code=%s, error=%s, path=%s"
                        .formatted(errRespData.getTimestamp(), errRespData.getStatus(), errRespData.getError(), errRespData.getPath()));
            }
        } catch (Exception e) {
            Assertions.fail("An exception was thrown  in \"register\": %s.".formatted(e));
        }
    }

    @Test
    void returns200_whenRefreshTokenWorks() {
        // Expected empty response = 200, else ErrorResponseObj
        try {
            request = RequestFactory.getRefreshTokenRequest(refreshTokenUrlStr);
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() != 200 && response.body().length != 0) {
                // wystąpił błąd
                errRespData = objectMapper.readValue(response.body(), ErrorResponseObj.class);
                Assertions.fail("Error at timestamp=%s, status code=%s, error=%s, path=%s"
                        .formatted(errRespData.getTimestamp(), errRespData.getStatus(), errRespData.getError(), errRespData.getPath()));
            }
        } catch (Exception e) {
            Assertions.fail("An exception was thrown in \"refresh-token\" : %s.".formatted(e));
        }
    }

    @Test
    void returns200_whenRegisterAuthenticateAndVerifyWorks() {
        final String firstName = UUID.randomUUID().toString();
        final String lastName = UUID.randomUUID().toString();
        final String email = "%s.%s@domain.com".formatted(firstName, lastName);
        final RegisterRequestObj registerRequestObj = new RegisterRequestObj(firstName, lastName, email, "password", Role.ADMIN,
                false);
        final AuthRequestObj authRequestObj = new AuthRequestObj(email, "password");
        String refreshToken = "";
// register test
        try {
            request = RequestFactory.getUserRegisterRequest(registerUrlStr, registerRequestObj);
            // Expected empty response = 200, response.body().length = 0, else ErrorResponseObj
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() != 200 && response.body().length != 0) {
                errRespData = objectMapper.readValue(response.body(), ErrorResponseObj.class);
                Assertions.fail("Error in \"authenticate\" at timestamp=%s, status code=%s, error=%s, path=%s"
                        .formatted(errRespData.getTimestamp(), errRespData.getStatus(), errRespData.getError(), errRespData.getPath()));
            }
// authenticate test
            request = RequestFactory.getAuthenticateRequest(authTokenUrlStr, authRequestObj);
            // Expected response code = 200, response.body = AuthResponseObj
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() == 200) {
                authRespData = objectMapper.readValue(response.body(), AuthResponseObj.class);
                refreshToken = authRespData.getRefreshToken();
            } else {
                // wystąpił błąd
                if (response.body().length != 0) {
                    errRespData = objectMapper.readValue(response.body(), ErrorResponseObj.class);
                    Assertions.fail("Error in \"authenticate\" at timestamp=%s, status code=%s, error=%s, path=%s"
                            .formatted(errRespData.getTimestamp(), errRespData.getStatus(), errRespData.getError(), errRespData.getPath()));
                }
// verify test
                final VerifyRequestObj verifyRequestObj = new VerifyRequestObj(email, refreshToken);
                request = RequestFactory.getVerifyRequest(verifyUrlStr, verifyRequestObj);
                // Expected: access token, mfaEnabled
                response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofByteArray());
                if (response.statusCode() == 200) {
                    VerifyResponseObj verifyResponseObj = objectMapper.readValue(response.body(), VerifyResponseObj.class);
                    MyLogger.debug("Verify data:\n access token=%s,\n mfaEnabled=%s"
                            .formatted(verifyResponseObj.getAccessToken(), verifyResponseObj.getMfaEnabled()));
                } else if (response.body().length != 0) {
                    // wystąpił błąd
                    errRespData = objectMapper.readValue(response.body(), ErrorResponseObj.class);
                    Assertions.fail("Error in \"verify\" at timestamp=%s, status code=%s, error=%s, path=%s"
                            .formatted(errRespData.getTimestamp(), errRespData.getStatus(), errRespData.getError(), errRespData.getPath()));
                }
            }
        } catch (Exception e) {
            Assertions.fail("An exception was thrown in \"verify\": %s.".formatted(e));
        }
    }

}