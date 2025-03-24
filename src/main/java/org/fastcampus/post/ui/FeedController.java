package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.principle.AuthPrincipal;
import org.fastcampus.common.principle.UserPrincipal;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal, Long lastPostId){
        List<GetPostContentResponseDto> result = queueQueryRepository.getContentResponse(userPrincipal.getUserId(), lastPostId);
        return Response.ok(result);
    }


}
