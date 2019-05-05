package com.car.domain.sys;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportSum {

    private BigDecimal transVols;

    private Integer maketTransCounts;

    private BigDecimal realDists;

}
