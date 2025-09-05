package pl.wblo.jwtdemo.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.jdi.request.InvalidRequestStateException;
import pl.wblo.jwtdemo.app.testobj.AuthRequestObj;
import pl.wblo.jwtdemo.app.testobj.RegisterRequestObj;
import pl.wblo.jwtdemo.app.testobj.VerifyRequestObj;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class RequestFactory {

    private static final Duration TEST_TIMEOUT_DURATION = Duration.ofMillis(1000);

    public static HttpRequest getUserRegisterRequest(String fullUrlString, RegisterRequestObj userData) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonStrBody = ow.writeValueAsString(userData);
            return HttpRequest.newBuilder()
                    .uri(URI.create(fullUrlString))
                    .method("POST", HttpRequest.BodyPublishers.ofString(jsonStrBody))
                    .timeout(TEST_TIMEOUT_DURATION)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();
        } catch (Exception ex) {
            throw new InvalidRequestStateException("Invalid register request: %s".formatted(ex.getMessage()));
        }
    }
    public static HttpRequest getRefreshTokenRequest(String fullUrlString) {

        try {
            return HttpRequest.newBuilder()
                    .uri(URI.create(fullUrlString))
                    .method("POST", HttpRequest.BodyPublishers.noBody())
                    .timeout(TEST_TIMEOUT_DURATION)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();
        } catch (Exception ex) {
            throw new InvalidRequestStateException("Invalid refresh-token request: %s".formatted(ex.getMessage()));
        }
    }

    public static HttpRequest getAuthenticateRequest(String fullUrlString, AuthRequestObj userData) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonStrBody = ow.writeValueAsString(userData);
            return HttpRequest.newBuilder()
                    .uri(URI.create(fullUrlString))
                    .method("POST", HttpRequest.BodyPublishers.ofString(jsonStrBody))
                    .timeout(TEST_TIMEOUT_DURATION)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();
        } catch (Exception ex) {
            throw new InvalidRequestStateException("Invalid authenticate request: %s".formatted(ex.getMessage()));
        }
    }

    public static HttpRequest getVerifyRequest(String fullUrlString, VerifyRequestObj userData) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonStrBody = ow.writeValueAsString(userData);
            return HttpRequest.newBuilder()
                    .uri(URI.create(fullUrlString))
                    .method("POST", HttpRequest.BodyPublishers.ofString(jsonStrBody))
                    .timeout(TEST_TIMEOUT_DURATION)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();
        } catch (Exception ex) {
            throw new InvalidRequestStateException("Invalid authenticate request: %s".formatted(ex.getMessage()));
        }
    }




}
