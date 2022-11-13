package com.preciseIT.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN(Code.ADMIN),
    COMPANYADMIN(Code.COMPANYADMIN),
    CLIENT(Code.CLIENT);

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String COMPANYADMIN = "ROLE_COMPANYADMIN";
        public static final String CLIENT = "ROLE_CLIENT";
    }

}