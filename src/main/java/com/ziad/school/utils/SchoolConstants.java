package com.ziad.school.utils;

import org.springframework.http.HttpHeaders;

public final class SchoolConstants {

    public static final String JWT_ISSUER = "School Management";

    public static final String JWT_HEADER = HttpHeaders.AUTHORIZATION;

    public static final String CLAIM_EMAIL = "email";

    public static final String CLAIM_AUTHORITIES = "authorities";

    public static final String SCHOOL_DENIED_REASON = "School-Denied-Reason";

    public static final String SCHOOL_Unauthorized_REASON = "School-Unauthorized-Reason";

}
