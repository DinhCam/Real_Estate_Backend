package com.gsu21se45.core.real_estate_search.transformer;

import com.gsu21se45.core.real_estate_search.dto.FacilityDto;
import com.gsu21se45.core.real_estate_search.dto.ImageDto;
import com.gsu21se45.core.real_estate_search.dto.RealEstateDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.Map;

public class RealEstateTransformer implements ResultTransformer {
    Map<Integer, RealEstateDto> result = new HashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            ImageDto img = getImageDto(tuples,aliasList);
            FacilityDto facilityDto = getFacilityDto(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getImages().add(img);
            result.get(tuples[aliasList.get("id")]).getFacilities().add(facilityDto);
        }
        else{
            RealEstateDto realEstateDto= getRealEstateDto(tuples,aliasList);
            result.put((Integer) tuples[aliasList.get("id")],realEstateDto);
        }
        return null;
}

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private RealEstateDto getRealEstateDto(Object[] tuples, Map<String, Integer> aliasList){
        RealEstateDto rs = new RealEstateDto();
        Set<ImageDto> imgs = new HashSet<>();
        Set<FacilityDto> facility = new HashSet<>();
        facility.add(getFacilityDto(tuples,aliasList));
        imgs.add(getImageDto(tuples,aliasList));
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setDescription(TypeTransformImpl.castObjectToString(tuples[aliasList.get("description")]));
        rs.setView(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("view")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setImages(imgs);
        rs.setFacilities(facility);
        rs.setArea((double)tuples[aliasList.get("area")]);
        rs.setPrice((double)tuples[aliasList.get("price")]);
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        return rs;
    }
    private ImageDto getImageDto(Object[] tuples, Map<String, Integer> aliasList){
        ImageDto  rs = new ImageDto();
        rs.setImgId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("imgId")]));
        rs.setImgUrl(TypeTransformImpl.castObjectToString(tuples[aliasList.get("imageUrl")]));
        return rs;
    }
    private FacilityDto getFacilityDto(Object[] tuples, Map<String, Integer> aliasList){
        FacilityDto  rs = new FacilityDto();
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("facilityId")]));
        rs.setFacilityName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("facilityName")]));
        rs.setFacilityType(TypeTransformImpl.castObjectToString(tuples[aliasList.get("facilityType")]));
        return rs;
    }
}
