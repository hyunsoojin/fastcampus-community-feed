package org.fastcampus.common.idempotency.repository.jpa;

import org.fastcampus.common.idempotency.Idempotency;
import org.fastcampus.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity,Long> {
    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
