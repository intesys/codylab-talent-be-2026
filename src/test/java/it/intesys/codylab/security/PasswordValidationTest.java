package it.intesys.codylab.security;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationTest {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$"
    );

    @Test
    void shouldAcceptValidPassword() {
        assertTrue(PASSWORD_PATTERN.matcher("Password1!").matches());
        assertTrue(PASSWORD_PATTERN.matcher("Strong@2024Pass").matches());
        assertTrue(PASSWORD_PATTERN.matcher("Abcdef1#xyz").matches());
    }

    @Test
    void shouldRejectPasswordWithoutUppercase() {
        assertFalse(PASSWORD_PATTERN.matcher("password1!").matches());
    }

    @Test
    void shouldRejectPasswordWithoutLowercase() {
        assertFalse(PASSWORD_PATTERN.matcher("PASSWORD1!").matches());
    }

    @Test
    void shouldRejectPasswordWithoutDigit() {
        assertFalse(PASSWORD_PATTERN.matcher("Password!").matches());
    }

    @Test
    void shouldRejectPasswordWithoutSpecialChar() {
        assertFalse(PASSWORD_PATTERN.matcher("Password1").matches());
    }

    @Test
    void shouldRejectShortPassword() {
        assertFalse(PASSWORD_PATTERN.matcher("Pa1!").matches());
    }
}
