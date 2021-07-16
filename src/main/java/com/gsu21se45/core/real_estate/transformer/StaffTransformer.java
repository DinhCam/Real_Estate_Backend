package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.StaffDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class StaffTransformer implements ResultTransformer {
    private StaffDto rs = new StaffDto();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);
        rs = getAllStaff(tuples, aliasList);
        return rs;
    }

    @Override
    public List transformList(List list) {
        return list;
    }

    private StaffDto getAllStaff(Object[] tuples, Map<String, Integer> aliasList){
        StaffDto rs = new StaffDto();
        rs.setId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("id")]));
        rs.setUsername(TypeTransformImpl.castObjectToString(tuples[aliasList.get("username")]));
        rs.setFullname(TypeTransformImpl.castObjectToString(tuples[aliasList.get("fullname")]));
        rs.setAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("avatar")]));
        return StringUtils.isEmpty(rs.getId()) ? null : rs;
    }
}
