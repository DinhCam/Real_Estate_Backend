package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.RealEstateTypeDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class RealEstateTypeTransformer implements ResultTransformer {
    private RealEstateTypeDto rs = new RealEstateTypeDto();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);
        rs = getAllRealEstateType(tuples, aliasList);
        return rs;
    }

    @Override
    public List transformList(List list) {
        return list;
    }

    private RealEstateTypeDto getAllRealEstateType(Object[] tuples, Map<String, Integer> aliasList){
        RealEstateTypeDto rs = new RealEstateTypeDto();
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("name")]));
        return StringUtils.isEmpty(rs.getId()) ? null : rs;
    }
}
