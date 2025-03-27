package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
@RequiredArgsConstructor
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {
//    private final FakeuserQueueRedisRepository fakeuserQueueRedisRepository;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
  //      List<PostEntity> postEntities = fakeuserQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();
        /*
        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .build();
            result.add(res);
        }
         */
        return result;
    }

}
