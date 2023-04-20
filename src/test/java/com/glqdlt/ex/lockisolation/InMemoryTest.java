package com.glqdlt.ex.lockisolation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author glqdlt
 */
@SpringBootTest
@ActiveProfiles("test")
public class InMemoryTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponEntityRepo couponEntityRepo;

    @BeforeEach
    void setUp() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(1);
        couponEntity.setLimit(10);
        couponEntity.setSerial("test");
        couponEntityRepo.saveAndFlush(couponEntity);

    }


    @Test
    void sample_use_lock() throws InterruptedException {

        final ExecutorService POOL = Executors.newFixedThreadPool(4);

        Assertions.assertEquals("test", couponService.findCoupon("test").getSerial());

        int i = 0;
        while (i < 4) {
            POOL.submit(() -> {
                couponService.useCouponWithLock("test");
            });
            i++;
        }

        POOL.awaitTermination(3, TimeUnit.SECONDS);

        Assertions.assertEquals(4, couponService.findCoupon("test").getQuantity());

    }

    @Test
    void sample_no_lock() throws InterruptedException {

        final ExecutorService POOL = Executors.newFixedThreadPool(4);

        Assertions.assertEquals("test", couponService.findCoupon("test").getSerial());

        int i = 0;
        while (i < 4) {
            POOL.submit(() -> {
                couponService.useCoupon("test");
            });
            i++;
        }

        POOL.awaitTermination(3, TimeUnit.SECONDS);

        Assertions.assertEquals(4, couponService.findCoupon("test").getQuantity());

    }
}
