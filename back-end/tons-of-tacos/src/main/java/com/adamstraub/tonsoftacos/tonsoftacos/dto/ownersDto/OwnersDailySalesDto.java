package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
public class OwnersDailySalesDto {
    private LocalDate date;
    private int numberOfSales;
    private double total;
}
