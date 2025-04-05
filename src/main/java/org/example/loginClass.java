package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class loginClass {
    private Map<String, String> users;

    public loginClass() {

        users = new HashMap<>();
        users.put("admin", "admin123");
        users.put("user", "user123");
    }

    /**
     * Authenticating a user by verifying the provided username and password.
     */
    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
