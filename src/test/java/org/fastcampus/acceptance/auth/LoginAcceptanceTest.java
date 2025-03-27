package org.fastcampus.acceptance.auth;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.LoginRequestDto;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "user1@test.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken(){
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password", "token");

        // when
        String token = requestLoginGetToken(dto);

        // then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero(){
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password", "token");

        // when
        Integer code = requestLoginGetResponseCode(dto);

        // then
        assertEquals(400, code);
    }

}
