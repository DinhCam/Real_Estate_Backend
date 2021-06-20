package com.gsu21se45.core.transaction.transformer;

import com.gsu21se45.core.real_estate_search.dto.FacilityDto;
import com.gsu21se45.core.real_estate_search.dto.ImageDto;
import com.gsu21se45.core.real_estate_search.dto.RealEstateDto;
import com.gsu21se45.core.transaction.dto.BuyerDto;
import com.gsu21se45.core.transaction.dto.GRealstateAssignedStaffDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.util.*;

public class RealEstateAssignedStaffTransformer implements ResultTransformer {
    Map<Integer, GRealstateAssignedStaffDto> result = new HashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("realEstateId")])){
            BuyerDto buyer = getBuyerDto(tuples,aliasList);
            result.get(tuples[aliasList.get("realEstateId")]).getBuyers().add(buyer);
        }
        else{
            GRealstateAssignedStaffDto gRealEstateAssignedStaffDto= getRealEstateAssignedStaffDto(tuples, aliasList);
            result.put((Integer) tuples[aliasList.get("realEstateId")],gRealEstateAssignedStaffDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private GRealstateAssignedStaffDto getRealEstateAssignedStaffDto(Object[] tuples, Map<String, Integer> aliasList){
        GRealstateAssignedStaffDto rs = new GRealstateAssignedStaffDto();
        rs.getBuyers().add(getBuyerDto(tuples, aliasList));
        rs.setRealEstateId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("realEstateId")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        return rs;
    }

    private BuyerDto getBuyerDto(Object[] tuples, Map<String, Integer> aliasList){
        BuyerDto  rs = new BuyerDto();
        rs.setBuyerId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("buyerId")]));
        return rs;
    }
}
