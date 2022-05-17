package com.example.attempt.security.util;

import java.util.regex.Pattern;

public class EmailValidator {
    public static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static Pattern compilePattern() {
        return Pattern.compile(EMAIL_PATTERN);
    }

    public static boolean validate(String emailAddress) {
        return compilePattern().matcher(emailAddress).matches();
    }
}
