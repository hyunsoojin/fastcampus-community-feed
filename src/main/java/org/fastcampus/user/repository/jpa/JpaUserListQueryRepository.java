package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.application.dto.GetUserListReponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListReponseDto(u.name, u.profileImageUrl) " +
            "FROM UserRelationEntity ur " +
            "INNER JOIN UserEntity u ON ur.followerUserId = u.id " +
            "WHERE ur.followingUserId = :userId")
    List<GetUserListReponseDto> getFollowingUserList(@Param("userId")Long userId);

    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListReponseDto(u.name, u.profileImageUrl) " +
            "FROM UserRelationEntity ur " +
            "INNER JOIN UserEntity u ON ur.followingUserId = u.id " +
            "WHERE ur.followerUserId = :userId")
    List<GetUserListReponseDto> getFollwerUserList(@Param("userId")Long userId);
}
