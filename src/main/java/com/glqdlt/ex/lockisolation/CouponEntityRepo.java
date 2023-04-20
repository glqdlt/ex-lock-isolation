package com.glqdlt.ex.lockisolation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface CouponEntityRepo extends JpaRepository<CouponEntity, Integer> {

    Optional<CouponEntity> findBySerial(String serial);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CouponEntity> findBySerialOrderByIdAsc(String serial);

}
