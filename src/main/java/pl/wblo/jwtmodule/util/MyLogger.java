package pl.wblo.jwtmodule.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wblo.JwtApplication;

/*
Klasa narzędziowa z prywatnym konstruktorem.
 */
public class MyLogger {
    private static final String USER_HEADER_MASK_STR = "%n__________ %s __________%n";
    private static final String USER_LOG_MASK ="********** %s **********";
    public static final Logger logger = LoggerFactory.getLogger(JwtApplication.class);
    private MyLogger() {}

    public static void trace(String logStr) {
        logger.trace(USER_LOG_MASK.formatted(logStr));
    }
    public static void debug(String logStr) {
        logger.debug(USER_LOG_MASK.formatted(logStr));
    }
    public static void header(String headerStr) {
        logger.info(USER_HEADER_MASK_STR.formatted(headerStr));
    }
}
