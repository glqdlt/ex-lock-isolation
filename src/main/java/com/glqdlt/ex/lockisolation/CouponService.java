package com.glqdlt.ex.lockisolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author glqdlt
 */
@Service
public class CouponService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

    @Autowired
    private CouponEntityRepo couponEntityRepo;

    @Transactional
    public void useCouponWithLock(String serial) {
        CouponEntity coupon = couponEntityRepo.findBySerialOrderByIdAsc(serial)
                .orElseThrow(() -> new IllegalArgumentException("Not found serial"));
        LOGGER.info("before coupon {} q {}", coupon.getSerial(), coupon.getQuantity());
        coupon.useCoupon();
        LOGGER.info("after coupon {} q {}", coupon.getSerial(), coupon.getQuantity());
        couponEntityRepo.save(coupon);
    }

    @Transactional
    public void useCoupon(String serial) {
        CouponEntity coupon = couponEntityRepo.findBySerial(serial)
                .orElseThrow(() -> new IllegalArgumentException("Not found serial"));
        LOGGER.info("before coupon {} q {}", coupon.getSerial(), coupon.getQuantity());
        coupon.useCoupon();
        LOGGER.info("after coupon {} q {}", coupon.getSerial(), coupon.getQuantity());
        couponEntityRepo.save(coupon);
    }

    public CouponEntity findCoupon(String serial) {
        return couponEntityRepo.findBySerial(serial)
                .orElseThrow(() -> new IllegalArgumentException("Not found serial"));
    }


}
