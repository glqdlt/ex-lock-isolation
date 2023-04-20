package com.glqdlt.ex.lockisolation;

import javax.persistence.*;

/**
 * @author glqdlt
 */
@Entity
@Table(name = "test.coupon")
public class CouponEntity {

    private Integer id;
    private String serial;
    private Integer quantity = 0;
    private Integer limit = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "serial")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "`limit`")
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void useCoupon() {
        int next = this.quantity + 1;
        if (limit < next) {
            throw new RuntimeException("Coupon limit over");
        }
        this.quantity = next;
    }
}
