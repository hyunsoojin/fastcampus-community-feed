package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.idempotency.Idempotent;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto){
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody CreatePostRequestDto dto){
        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

    @Idempotent
    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto){
        postService.likePost(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto){
        postService.unlikePost(dto);
        return Response.ok(null);
    }

}
