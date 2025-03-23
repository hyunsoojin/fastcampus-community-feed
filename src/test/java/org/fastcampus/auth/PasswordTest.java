package org.fastcampus.auth;

import org.fastcampus.auth.domain.Password;
import org.fastcampus.auth.domain.SHA256;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue(){
        Password password = Password.createEncryptPassword("password");
        assertTrue(password.matchPassword("password"));

    }

    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnTrue(){
        Password password = Password.createEncryptPassword("password1");
        assertFalse(password.matchPassword("password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordisNull_thenThrowError(String password){
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }

}
