package org.example;
public class CreateUserAccount {

    //length range for the username
    public static final int MIN_USERNAME_LENGTH = 5;
    public static final int MAX_USERNAME_LENGTH = 15;

    //length range for the password
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 16;

    //sample username and emails we can test on whether or mot they exist
    public static String[] existingUsernames = {"johndoe", "alice123", "mike_the_great"};
    public static String[] existingEmails = {"john@example.com", "alice@example.org"};

    //check for length
    public static boolean isUsernameLengthValid(String username) {
        return username.length() >= MIN_USERNAME_LENGTH && username.length() <= MAX_USERNAME_LENGTH;
    }

    //check if previously exists
    public static boolean isUsernameUnique(String username) {
        for (String existing : existingUsernames) {
            if (existing.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    //check so same email is not used twice
    public static boolean isEmailUnique(String email) {
        for (String existing : existingEmails) {
            if (existing.equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    //format  of the email
    public static boolean isEmailValidFormat(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf("@") < email.lastIndexOf(".");
    }

    //check for the length of the password and if has certain characteristics
    public static boolean isPasswordTooShort(String password) {
        return password.length() < MIN_PASSWORD_LENGTH;
    }

    public static boolean isPasswordTooLong(String password) {
        return password.length() > MAX_PASSWORD_LENGTH;
    }

    public static boolean hasLetterDigitSpecial(String password) {
        boolean hasLetter = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        return hasLetter && hasDigit && hasSpecial;
    }



    //constructor to test our testcases if they give these errors
    public static String createUser(String username, String email, String pwd1) {
        if (!isUsernameLengthValid(username)) return "Username length invalid";
        if (!isUsernameUnique(username)) return "Username already taken";
        if (!isEmailValidFormat(email)) return "Email format invalid";
        if (!isEmailUnique(email)) return "Email already registered";
        if (isPasswordTooShort(pwd1)) return "Password too short";
        if (isPasswordTooLong(pwd1)) return "Password too long";
        if (!hasLetterDigitSpecial(pwd1)) return "Password must include a letter, a digit, and a special character";
        return "Account created successfully";
    }

}

