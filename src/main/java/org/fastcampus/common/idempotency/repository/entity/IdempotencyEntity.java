package org.fastcampus.common.idempotency.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.idempotency.Idempotency;
import org.fastcampus.common.utils.ResponseObjectMapper;

@Entity
@Table(name = "community_idempotency")
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    @Getter
    @Column(nullable = false)
    private String response;

    public IdempotencyEntity(Idempotency idempotencyKey) {
        this.idempotencyKey = idempotencyKey.getKey();
        this.response = ResponseObjectMapper.toStringResponse(idempotencyKey.getResponse());
    }

    public Idempotency toIdempotency(){
        return new Idempotency(this.idempotencyKey, ResponseObjectMapper.toResponseObject(response));
    }


}
