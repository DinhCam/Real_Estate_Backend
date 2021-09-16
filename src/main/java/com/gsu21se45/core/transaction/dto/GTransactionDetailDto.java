package com.gsu21se45.core.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsu21se45.core.real_estate.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GTransactionDetailDto {
    private int id;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String sellerPhone;
    private String sellerEmail;
    private String buyerId;
    private String buyerName;
    private String buyerAvatar;
    private String buyerPhone;
    private String buyerEmail;
    private String staffId;
    private String staffName;
    private String staffAvatar;
    private String staffPhone;
    private String staffEmail;
    private int realEstateId;
    private String realEstateTitle;
    private String streetName;
    private String wardName;
    private String disName;
    private double dealPrice;
    private String note;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp appointmentDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;

    private Set<ImageDto> images;
}
