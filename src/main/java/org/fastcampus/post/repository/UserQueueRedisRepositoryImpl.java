package org.fastcampus.post.repository;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {
    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToFollowerUserList(List<PostEntity> postEntities, Long userId) {

    }

    @Override
    public void deleteDeleteFeed(Long userId, Long targetUserId) {

    }
}
