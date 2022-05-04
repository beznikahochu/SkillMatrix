package by.skillmatrix.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String EXAMPLE_LONG = "13";

    public static final String DESCRIPTION_NAME = "User name";
    public static final String EXAMPLE_NAME = "Bob";

    public static final String DESCRIPTION_LOGIN = "User email";
    public static final String EXAMPLE_LOGIN = "b.swagger@swagger.com";
    public static final String DESCRIPTION_WORD_PASS = "Password";
    public static final String EXAMPLE_WORD_PASS = "12345qwer";
    public static final String DESCRIPTION_TOKEN = "Jwt token";
    public static final String EXAMPLE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
            + ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ"
            + ".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

}
