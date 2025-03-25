package org.fastcampus.post.repository;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!test")
public class UserPostqueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return List.of();
    }
}
