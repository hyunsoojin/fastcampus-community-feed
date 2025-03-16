package org.fastcampus.post.repository.post_queue;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserQueueRedisRepository redisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> folowersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        redisRepository.publishPostToFollowingUserList(postEntity, folowersIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        redisRepository.publishPostListToFollowerUserList(postEntities, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        redisRepository.deleteDeleteFeed(userId, targetId);
    }
}
