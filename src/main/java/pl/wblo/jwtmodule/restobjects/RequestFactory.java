package pl.wblo.jwtmodule.restobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.jdi.request.InvalidRequestStateException;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class RequestFactory {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    private static final Duration TEST_TIMEOUT_DURATION = Duration.ofMillis(1000);

    public static HttpRequest getUserRegisterRequest(String fullUrlString, RegisterRequestObj userData) {
        try {
            String jsonStrBody = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(userData);
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
            String jsonStrBody = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(userData);
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
            String jsonStrBody = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(userData);
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
