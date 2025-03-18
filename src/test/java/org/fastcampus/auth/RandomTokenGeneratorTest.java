package org.fastcampus.auth;

import org.fastcampus.auth.domain.RandomTokenGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTokenGeneratorTest {

    @Test
    void whenGenerateToken_thenReturnTokenWithCorrectLength(){
        //when
        String token = RandomTokenGenerator.getnerateToken();

        //then
        assertNotNull(token);
        assertEquals(16, token.length());
    }

    @Test
    void whenGenerateToken_thenReturnTokenWithValidCharacters(){
        //when
        String token = RandomTokenGenerator.getnerateToken();

        //then
        assertNotNull(token);
        assertEquals(16, token.length());
        assertTrue(token.matches("[0-9a-zA-Z]{16}"));
    }

    @Test
    void whenGenerateTokenMultipleTimes_thenReturnUniqueToken(){
        //when
        String token1 = RandomTokenGenerator.getnerateToken();
        String token2 = RandomTokenGenerator.getnerateToken();

        //then
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2);
    }

}
