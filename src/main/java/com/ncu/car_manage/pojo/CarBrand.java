package com.ncu.car_manage.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * CAR_BRAND
 * @author 
 */
@Data
public class CarBrand implements Serializable {
    private BigDecimal id;

    private String brand;

    private static final long serialVersionUID = 1L;
}