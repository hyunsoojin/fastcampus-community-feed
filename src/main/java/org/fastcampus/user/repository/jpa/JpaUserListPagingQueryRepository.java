package org.fastcampus.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.dto.GetUserListReponseDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.fastcampus.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListReponseDto> getFollwerList(Long userId, Long lastFollwerId){
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetUserListReponseDto.class
                        )
                )
                .from(relation)
                .join(user).on(relation.followingUserId.eq(user.id))
                .where(
                        relation.followerUserId.eq(userId)
                        ,hasLastData(lastFollwerId)
                )
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();
    }
    private BooleanExpression hasLastData(Long lastId){
        if (lastId == null){
            return null;
        }
        return user.id.lt(lastId);
    }
}
