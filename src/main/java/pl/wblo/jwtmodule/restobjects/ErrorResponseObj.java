package pl.wblo.jwtmodule.restobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class ErrorResponseObj implements Serializable {
    private Date timestamp;
    private int status;
    private String error;
    private String path;

    //        String ErrorResponseBody = """
    //                {
    //                  "timestamp": "2025-09-05T17:58:27.477+00:00",
    //                  "status": 500,
    //                  "error": "Internal Server Error",
    //                  "path": "/api/v1/auth/register"
    //                } """;
}
