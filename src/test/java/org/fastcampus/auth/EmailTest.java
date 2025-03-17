package org.fastcampus.auth;

import org.fastcampus.auth.domain.Email;
import org.hibernate.annotations.Parameter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {
    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreateEmail_thenThrowError(String email){
        //then
        assertThrows(IllegalArgumentException.class, ()->Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "natty#@naver", "안녕@하세요.com"})
    void givenInvalidEmail_whenCreateEmail_thenThrowError(String email){
        //then
        assertThrows(IllegalArgumentException.class, ()->Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a@naver.com", "natty@naver.com", "test@test.com"})
    void givenValidEmail_whenCreateEmail_thenReturnEmail(String email){
        //given

        //when
        Email emailValue = Email.createEmail(email);

        assertEquals(email, emailValue.getEmailText());
    }
}
