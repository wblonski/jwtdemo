package pl.wblo.jwtmodule.restobjects;

import lombok.Data;

import java.io.Serializable;

    @Data
    public class VerifyRequestObj implements Serializable {
        private String email;
        private String code;

        public VerifyRequestObj() {}

        public VerifyRequestObj(String email, String code) {
            this.email = email;
            this.code = code;
        }

}
