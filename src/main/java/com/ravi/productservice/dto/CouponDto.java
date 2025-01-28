package com.ravi.productservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class CouponDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;
    @Column
    private BigDecimal discount;
    @Column
    private String exp_date;

    public CouponDto() {}

    public CouponDto(String code, BigDecimal discount, String exp_date) {
        this.code = code;
        this.discount = discount;
        this.exp_date = exp_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    @Override
    public String toString() {
        return "CouponDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", exp_date='" + exp_date + '\'' +
                '}';
    }
}
