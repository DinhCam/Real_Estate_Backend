package com.gsu21se45.core.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CTransactionDto {
    private int buyerId;
    private int sellerId;
    private int staffId;
    private int realEstateId;
    private double downPrice;
    private Timestamp createAt;
}
