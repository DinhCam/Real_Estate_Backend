package com.gsu21se45.core.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CTransactionDto {
    private String buyerId;
    private String sellerId;
    private String staffId;
    private int realEstateId;
    private String img_url;
    private double deposit;
    private double downPrice;
    private String note;
    private Date createAt;
}