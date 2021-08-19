package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.real_estate.dto.StaffDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.util.*;

public class StaffTransformer implements ResultTransformer {
    Map<String, StaffDto> result = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            DistrictDto districtDto = getDistrictName(tuples, aliasList);
            result.get(tuples[aliasList.get("id")]).getWorkingArea().add(districtDto);
        }
        else {
            StaffDto staffDto = getAllStaff(tuples,aliasList);
            result.put((String) tuples[aliasList.get("id")], staffDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private StaffDto getAllStaff(Object[] tuples, Map<String, Integer> aliasList){
        StaffDto rs = new StaffDto();
        Set<DistrictDto> districtDtos = new HashSet<>();
        districtDtos.add(getDistrictName(tuples, aliasList));
        rs.setWorkingArea(districtDtos);
        rs.setId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("id")]));
        rs.setFullname(TypeTransformImpl.castObjectToString(tuples[aliasList.get("fullname")]));
        rs.setAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("avatar")]));
        rs.setPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("phone")]));
        return StringUtils.isEmpty(rs.getId()) ? null : rs;
    }

    private DistrictDto getDistrictName(Object[] tuples, Map<String, Integer> aliasList){
        DistrictDto rs = new DistrictDto();
        rs.setName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("disName")]));
        return rs;
    }
}
