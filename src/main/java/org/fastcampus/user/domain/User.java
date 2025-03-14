package org.fastcampus.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private UserInfo info;
    private PositiveIntegerCounter followingCount;
    private PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if (targetUser.equals(this)){
            throw new IllegalArgumentException();
        }
        followingCount.increase();
        targetUser.increaseFollowerCount();
    }


    public void unfollow(User targetUser){
        if (targetUser.equals(this)){
            throw new IllegalArgumentException();
        }
        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }


    private void increaseFollowerCount(){
        followerCount.increase();
    }

    private void decreaseFollowerCount(){
        followerCount.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int followingCount() {
        return followingCount.getCount();
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int getFollowingCount() {
        return followingCount.getCount();
    }

    public int getFollowerCount() {
        return followerCount.getCount();
    }

    public String getName() {
        return info.getName();
    }
    public String getProfileImageUrl() {
        return info.getProfileImageUrl();
    }


}
