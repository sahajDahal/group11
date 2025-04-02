package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class loginClass {
    private Map<String, String> users;

    public loginClass() {
        // Initialize the user store with predefined users
        users = new HashMap<>();
        users.put("admin", "admin123"); // default admin credentials
        users.put("user", "user123");   // default user credentials
    }

    /**
     * Authenticate a user by verifying the provided username and password.
     *
     * @param username the username to authenticate
     * @param password the password to verify
     * @return true if the credentials are correct; false otherwise
     */
    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
