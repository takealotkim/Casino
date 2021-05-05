package com.rank.casino.utils;

import com.rank.casino.error.InvalidPasswordException;

public class SecurityUtil {
    public static void verifyPassword(String password) throws InvalidPasswordException {
        if (!password.equalsIgnoreCase("swordfish"))
            throw new InvalidPasswordException();
    }
}
