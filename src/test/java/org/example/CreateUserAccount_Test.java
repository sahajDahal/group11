package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserAccount_Test {

    @Test
    void testValidAccountCreation() {
        assertEquals("Account created successfully",
                CreateUserAccount.createUser("newuser", "newuser@email.com", "Abc123!@"));
    }

    @Test
    void testShortUsername() {
        assertEquals("Username length invalid",
                CreateUserAccount.createUser("abc", "short@email.com", "Abc123!@"));
    }

    @Test
    void testLongUsername() {
        assertEquals("Username length invalid",
                CreateUserAccount.createUser("averyverylongusername", "long@email.com", "Abc123!@"));
    }

    @Test
    void testDuplicateUsername() {
        assertEquals("Username already taken",
                CreateUserAccount.createUser("johndoe", "unique@email.com", "Abc123!@"));
    }

    @Test
    void testInvalidEmailFormat() {
        assertEquals("Email format invalid",
                CreateUserAccount.createUser("uniqueuser", "invalid-email", "Abc123!@"));
    }

    @Test
    void testDuplicateEmail() {
        assertEquals("Email already registered",
                CreateUserAccount.createUser("uniqueuser", "john@example.com", "Abc123!@"));
    }

  
    @Test
    void testWeakPasswordMissingSpecialChar() {
        assertEquals("Password must include a letter, a digit, and a special character",
                CreateUserAccount.createUser("uniqueuser", "unique@email.com", "Abc12345"));
    }

    @Test
    void testPasswordTooShort() {
        assertEquals("Password too short",
                CreateUserAccount.createUser("uniqueuser", "unique@email.com", "Ab1!"));
    }

    @Test
    void testPasswordTooLong() {
        assertEquals("Password too long",
                CreateUserAccount.createUser("uniqueuser", "unique@email.com", "Abcdef1234567890!"));
    }



}