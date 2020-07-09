package com.ncu.car_manage.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * CAR_RECORD
 * @author 
 */
@Data
public class CarRecord implements Serializable {
    private BigDecimal id;

    private String username;

    private BigDecimal operationType;//1:add 2:update 3:delete

    private Date operateTime;

    private static final long serialVersionUID = 1L;
}