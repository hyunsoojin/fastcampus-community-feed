package org.fastcampus.post.repository;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class FakeuserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId : userIdList) {
            if (queue.containsKey(userId)) {
                queue.get(userId).add(postEntity);
            } else {
                queue.put(userId, new HashSet<>(List.of(postEntity)));
            }
        }
    }

    @Override
    public void publishPostListToFollowerUserList(List<PostEntity> postEntities, Long userId) {
        if(queue.containsKey(userId)){
            queue.get(userId).addAll(postEntities);
        } else {
            queue.put(userId, new HashSet<>(postEntities));
        }
    }

    @Override
    public void deleteDeleteFeed(Long userId, Long targetUserId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).removeIf(post -> post.getAuthor().getId().equals(targetUserId));
        }
    }

    public List<PostEntity> getPostListByUserId(Long userId){
        return List.copyOf(queue.get(userId));
    }
}
