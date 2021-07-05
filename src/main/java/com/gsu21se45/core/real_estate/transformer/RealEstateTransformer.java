package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.RealEstateDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RealEstateTransformer implements ResultTransformer {
    private RealEstateDto result = new RealEstateDto();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);
        result = getAllRealEstates(tuples, aliasList);
        return result;
    }

    @Override
    public List transformList(List list) {
        return list;
    }

    private RealEstateDto getAllRealEstates(Object[] tuples, Map<String, Integer> aliasList){
        RealEstateDto rs = new RealEstateDto();
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setDescription(TypeTransformImpl.castObjectToString(tuples[aliasList.get("description")]));
        rs.setView(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("view")]));
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setArea((double)tuples[aliasList.get("area")]);
        rs.setPrice((double)tuples[aliasList.get("price")]);
        rs.setNumberOfBedroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBedroom")]));
        rs.setNumberOfBathroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBathroom")]));
        rs.setProject(TypeTransformImpl.castObjectToString(tuples[aliasList.get("project")]));
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        rs.setTypeName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("typeName")]));
        rs.setAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("avatar")]));
        return rs;
    }
}
