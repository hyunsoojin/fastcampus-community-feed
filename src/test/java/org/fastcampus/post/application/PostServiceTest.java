package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost(){
        //when
        Post savePost = postService.createPost(postRequestDto);
        //then
        Post foundPost = postService.getPost(savePost.getId());
        assertEquals(savePost, foundPost);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatePost(){
        //given
        Post savePost = postService.createPost(postRequestDto);
        //when
        Post updatePost = postService.updatePost(savePost.getId(), postRequestDto);
        //then
        assertEquals(savePost.getId(), updatePost.getId());
        assertEquals(savePost.getAuthor(), updatePost.getAuthor());
        assertEquals(savePost.getContent(), updatePost.getContent());
    }

    @Test
    void givenCreatePost_whenLike_thenIncreaseLikeCount(){
        //given
        Post savePost = postService.createPost(postRequestDto);
        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        //then
        assertEquals(1, savePost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenUnlike_thenDecreaseLikeCount(){
        //given
        Post savePost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        //when
        postService.unlikePost(likeRequestDto);
        //then
        assertEquals(0, savePost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnlike_thenReturnPostWithoutLike(){
        //given
        Post savePost = postService.createPost(postRequestDto);
        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);
        //then
        assertEquals(0, savePost.getLikeCount());
    }

}
