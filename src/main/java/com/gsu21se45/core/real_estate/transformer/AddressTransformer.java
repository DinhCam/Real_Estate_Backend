package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.AddressDto;
import com.gsu21se45.core.real_estate.dto.WardDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressTransformer implements ResultTransformer {
    Map<Integer, AddressDto> result = new HashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            WardDto ward = getWard(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getWards().add(ward);
        }
        else{
            AddressDto addressDto = getAddress(tuples, aliasList);
            result.put((Integer) tuples[aliasList.get("id")],addressDto);
        }
        return null;
    }

    @Override
    public List transformList(List list){
        return new ArrayList(result.values());
    }

    private AddressDto getAddress(Object[] tuples, Map<String, Integer> aliasList){
        AddressDto rs = new AddressDto();
        if (getWard(tuples, aliasList) != null){
            List<WardDto> wards = new ArrayList<>();
            wards.add(getWard(tuples, aliasList));
            rs.setWards(wards);
        }
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setDisName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("disName")]));
        return rs;
    }

    private WardDto getWard(Object[] tuples, Map<String, Integer> aliasList){
        WardDto  rs = new WardDto();
        rs.setWardId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("wardId")]));
        rs.setWardName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("wardName")]));
        return StringUtils.isEmpty(rs.getWardId()) ? null : rs;
    }
}
