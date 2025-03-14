package org.fastcampus.user.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.dto.GetUserListReponseDto;
import org.fastcampus.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relation")
public class UserRelationController {

    private final UserRelationService userRelationService;


    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }

}
