package com.essaadani.securityservice.sec;

public class JWTUtil {
    public static final String SECRET = "secret";
    public static final String PREFIX = "Bearer ";
    // 15MIN
    public static final Long EXPIRE_ACCESS_TOKEN = 15L*60*1000;
    // 60D
    public static final Long EXPIRE_REFRESH_TOKEN = 86400L*60*1000;
}
