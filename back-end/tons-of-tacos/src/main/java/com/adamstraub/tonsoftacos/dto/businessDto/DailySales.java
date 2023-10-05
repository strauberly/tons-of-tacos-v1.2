package com.adamstraub.tonsoftacos.dto.businessDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class DailySales {
    private LocalDate date;
    private int numberOfSales;
//    private double total;
private BigDecimal total;
}
