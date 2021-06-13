package com.gsu21se45.core.real_estate_search.transformer;

import com.gsu21se45.core.real_estate_search.dto.RealEstateDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.util.List;
import java.util.Map;

public class RealEstateTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);
        return getRealEstateDto(tuples,aliasList);
    }

    @Override
    public List transformList(List list) {
        return list;
    }

    private RealEstateDto getRealEstateDto(Object[] tuples, Map<String, Integer> aliasList){
        RealEstateDto rs = new RealEstateDto();
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setView(TypeTransformImpl.castObjectToString(tuples[aliasList.get("view")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        return rs;
    }
}
