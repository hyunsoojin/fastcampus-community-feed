package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.content.CommentContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        //given
        String text = "this is a test";

        //when
        CommentContent content = new CommentContent(text);

        //then
        assertEquals(text, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError(){
        //given
        String content = "a".repeat(101);

        //when,then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁","닭","굵","삵","슱"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord){
        //given
        String content = koreanWord.repeat(101);

        //when,then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value){
        //when ,then
        assertThrows(IllegalArgumentException.class, ()-> new CommentContent(value));
    }
}
