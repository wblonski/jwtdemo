package pl.wblo.jwtmodule.restobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VerifyRequestObj implements Serializable {
    private String email;
    private String code;


}
