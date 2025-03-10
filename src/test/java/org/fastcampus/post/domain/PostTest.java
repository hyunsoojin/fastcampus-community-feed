package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreatedwhenLikethenLikeCountShouldBe1(){
        //when
        post.like(otherUser);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreatedwhenUnlikeThenLikeCountShouldBe0(){
        //given
        post.like(otherUser);

        //when
        post.unlike(otherUser);

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated(){
        //given
        String updatedContent = "updated content";
        PostContent postContent = new PostContent(updatedContent);

        //when
        post.updatePost(user, updatedContent, null);

        //then
        assertEquals(updatedContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUser_thenThrowError(){
        //when,then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, "updated content", null));
    }

}
