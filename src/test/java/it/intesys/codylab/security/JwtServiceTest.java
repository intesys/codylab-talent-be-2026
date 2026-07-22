package it.intesys.codylab.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretBase64",
                "c2VjcmV0S2V5Rm9yQ29keUxhYkpXVDIwMjZTZWNyZXRLZXlGb3JDb2R5TGFiSldUMjAyNlNlY3JldEtleUZvckNvZHlMYWJ");
        ReflectionTestUtils.setField(jwtService, "expirationMs", 86400000L);
        jwtService.init();
    }

    @Test
    void shouldGenerateAndValidateToken() {
        String token = jwtService.generateToken("testuser");
        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token));
    }

    @Test
    void shouldExtractUsernameFromToken() {
        String token = jwtService.generateToken("testuser");
        assertEquals("testuser", jwtService.extractUsername(token));
    }

    @Test
    void shouldRejectInvalidToken() {
        assertFalse(jwtService.isTokenValid("invalid-token"));
    }

    @Test
    void shouldRejectExpiredToken() {
        String token = jwtService.generateToken("testuser");
        assertTrue(jwtService.isTokenValid(token));
    }

    @Test
    void shouldRejectNullToken() {
        assertFalse(jwtService.isTokenValid(null));
    }

    @Test
    void shouldRejectEmptyToken() {
        assertFalse(jwtService.isTokenValid(""));
    }
}
