package pl.wblo.jwtmodule.restobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class ErrorResponseObj implements Serializable {

    private Date timestamp;
    private int status;
    private String error;
    private String path;

    public ErrorResponseObj() {
    }

    public ErrorResponseObj(Date timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
    //        String ErrorResponseBody = """
    //                {
    //                  "timestamp": "2025-09-05T17:58:27.477+00:00",
    //                  "status": 500,
    //                  "error": "Internal Server Error",
    //                  "path": "/api/v1/auth/register"
    //                } """;
}
