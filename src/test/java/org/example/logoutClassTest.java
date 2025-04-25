package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogoutTest {

    //That users can successfully log in and log out
    @Test
    void testLogoutSuccess() {
        logoutClass logoutSystem = new logoutClass();
        logoutSystem.login("testUser");

        assertTrue(logoutSystem.isLoggedIn("testUser")); // User should be logged in
        assertTrue(logoutSystem.logout("testUser")); // Logout should be successful
        assertFalse(logoutSystem.isLoggedIn("testUser")); // User should be logged out
    }

    //That logout fails if the user was not logged in.
    @Test
    void testLogoutFailure_NotLoggedIn() {
        logoutClass logoutSystem = new logoutClass();

        assertFalse(logoutSystem.logout("testUser")); // Should fail because user wasn't logged in
    }

    //That multiple users can be tracked independently.
    @Test
    void testLogoutAfterMultipleLogins() {
        logoutClass logoutSystem = new logoutClass();
        logoutSystem.login("user1");
        logoutSystem.login("user2");

        assertTrue(logoutSystem.isLoggedIn("user1"));
        assertTrue(logoutSystem.isLoggedIn("user2"));

        assertTrue(logoutSystem.logout("user1"));
        assertFalse(logoutSystem.isLoggedIn("user1"));
        assertTrue(logoutSystem.isLoggedIn("user2")); // User2 should still be logged in
    }

    //That a user can log in and log out multiple times without issues.
    @Test
    void testLoginAndLogoutMultipleTimes() {
        logoutClass logoutSystem = new logoutClass();

        logoutSystem.login("user123");
        assertTrue(logoutSystem.isLoggedIn("user123"));

        assertTrue(logoutSystem.logout("user123"));
        assertFalse(logoutSystem.isLoggedIn("user123"));

        logoutSystem.login("user123");
        assertTrue(logoutSystem.isLoggedIn("user123"));
    }
}