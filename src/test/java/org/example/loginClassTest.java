package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    private loginClass loginSystem;

    @BeforeEach
    void setUp() {
        loginSystem = new loginClass();
    }

    @Test
    void login_TC1() {
        // Successful login with a valid user and correct password
        assertTrue(loginSystem.authenticate("admin", "admin123"));
    }

    @Test
    void login_TC2() {
        // Unsuccessful login due to incorrect password
        assertFalse(loginSystem.authenticate("admin", "WrongPassword"));
    }

    @Test
    void login_TC3() {
        // Unsuccessful login with a non-existent user
        assertFalse(loginSystem.authenticate("nonexistentuser@hmail.com", "SomePassword123!"));
    }

    @Test
    void login_TC4() {
        // Empty username should not authenticate
        assertFalse(loginSystem.authenticate("", "SomePassword123!"));
    }

    @Test
    void login_TC5() {
        // Empty password should not authenticate
        assertFalse(loginSystem.authenticate("admin", ""));
    }


}