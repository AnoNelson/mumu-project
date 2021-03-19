package gov.rw.brd.security;

/**
 * Created by Nick on 1/16/2020.
 */
public class SecurityConstraint {
    public static final String SECRET = "secretKeyToGenerateJwt";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME= 30000_000;
}
