package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("comment"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1(){
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowError(){
        //when,then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBe0(){
        //given
        comment.like(otherUser);

        //when
        comment.unlike();

        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe0(){
        //when
        comment.unlike();

        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUserContent_thenThrowError(){
        //when,then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, "updated content"));
    }

    @Test
    void givenComment_whenUpdateContent_thenShouldBeUpdated(){
        //given
        String updatedContent = "updated content";

        //when
        comment.updateComment(user, updatedContent);

        //then
        assertEquals(updatedContent, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateContentOver100_thenThrowError(){
        //given
        String updatedContent = "a".repeat(101);

        //when,then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, updatedContent));
    }

}
