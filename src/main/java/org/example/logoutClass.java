package org.example;
import java.util.Map;
import java.util.HashMap;
public class logoutClass {
    private Map<String, Boolean> userSessions; // To track logged-in users

    public logoutClass() {
        // Initialize a map to track user sessions
        userSessions = new HashMap<>();
    }

    /**
     * Log out the user by removing them from the active session map.
     *
     * @param username the username to log out
     * @return true if the user was successfully logged out, false if the user was not logged in
     */
    public boolean logout(String username) {
        if (userSessions.containsKey(username) && userSessions.get(username)) {
            // Remove user from the active session (set their status to false)
            userSessions.put(username, false);
            return true; // Successfully logged out
        }
        return false; // User was not logged in
    }

    /**
     * Simulate logging in a user and tracking their session.
     *
     * @param username the username to log in
     */
    public void login(String username) {
        userSessions.put(username, true); // Mark user as logged in
    }

    /**
     * Check if a user is currently logged in.
     *
     * @param username the username to check
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn(String username) {
        return userSessions.getOrDefault(username, false);
    }
}
