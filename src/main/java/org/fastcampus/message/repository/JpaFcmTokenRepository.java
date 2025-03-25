package org.fastcampus.message.repository;

import org.fastcampus.message.repository.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {

}
