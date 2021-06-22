package com.gsu21se45.core.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDto {
    private String buyerId;
    private String buyerName;
    private String avatar;
}