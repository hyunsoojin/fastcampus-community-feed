package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment(){
        //when
        Comment comment = commentService.createComment(commentRequestDto);

        //then
        String content = comment.getContent();
        assertEquals(content, commentContent);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdateComment(){
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "this is update comment");
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);

        //then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(comment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenComment_whenLikeCOmmnet_thenIncreaseLikeCount(){
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithoutLike(){
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        //then
        assertEquals(0, comment.getLikeCount());
    }

}
