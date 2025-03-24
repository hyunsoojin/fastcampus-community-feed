package org.fastcampus.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImageUrl;
    private Integer followingCount;
    private Integer followerCount;
    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;

    @OneToMany(fetch = FetchType.EAGER)
    private List<PostEntity> posts;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followingCount = user.followingCount();
        this.followerCount = user.followerCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImageUrl))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .followerCount(new PositiveIntegerCounter((followerCount)))
                .build();
    }
}
