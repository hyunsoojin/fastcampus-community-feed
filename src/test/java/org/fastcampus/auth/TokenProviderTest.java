package org.fastcampus.auth;

import org.fastcampus.auth.domain.TokenProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenProviderTest {

    private final String secretKey = "testetsetsetsettestetsetsetsettestetsetsetsettestetsetsetsettestetsetsetset";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValiduserAndRole_whenCreateToken_thenReturnValidToken(){
        // given
        Long userId = 1L;
        String role = "ADMIN";
        // when
        String token = tokenProvider.createToken(userId, role);

        // then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));

    }

    @Test
    void givenVInalidToken_whenGetUserId_thenThrowError(){
        // given
        String invalidToken = "invalidToken";
        // when

        // then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }

}
