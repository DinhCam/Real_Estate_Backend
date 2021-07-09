package com.gsu21se45.core.real_estate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDto {
    private String buyerId;
    private String buyerName;
    private String buyerAvatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerDto buyerDto = (BuyerDto) o;
        return Objects.equals(buyerId, buyerDto.buyerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId);
    }
}