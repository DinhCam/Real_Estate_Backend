package com.gsu21se45.core.transaction.dto;

import com.gsu21se45.entity.ImageResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CTransactionDto {
    private String buyerId;
    private String sellerId;
    private String staffId;
    private int realEstateId;
    private double dealPrice;
    private String note;
    private String reason;
    private Date createAt;
    private List<ImageResource> images;
}