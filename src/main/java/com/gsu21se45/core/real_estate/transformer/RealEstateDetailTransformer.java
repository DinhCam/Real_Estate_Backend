package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.FacilityDto;
import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.core.real_estate.dto.RealEstateDetailDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.Map;

public class RealEstateDetailTransformer implements ResultTransformer {
     Map<Integer, RealEstateDetailDto> result = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            FacilityDto facilityDto = getFacilityDto(tuples,aliasList);
            if (facilityDto != null){
                result.get(tuples[aliasList.get("id")]).getFacilities().add(facilityDto);
            }

            ImageDto img = getImageDto(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getImages().add(img);
        }
        else{
            RealEstateDetailDto realEstateDetailDto = getRealEstateDetailDto(tuples,aliasList);
            result.put((Integer) tuples[aliasList.get("id")], realEstateDetailDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private RealEstateDetailDto getRealEstateDetailDto(Object[] tuples, Map<String, Integer> aliasList){
        RealEstateDetailDto rs = new RealEstateDetailDto();
        if (getFacilityDto(tuples, aliasList) != null){
            rs.getFacilities().add(getFacilityDto(tuples, aliasList));
        }
        Set<ImageDto> imgs = new HashSet<>();
        imgs.add(getImageDto(tuples,aliasList));
        rs.setImages(imgs);
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
        rs.setDirection(TypeTransformImpl.castObjectToString(tuples[aliasList.get("direction")]));
        rs.setNumberOfBedroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBedroom")]));
        rs.setNumberOfBathroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBathroom")]));
        rs.setProject(TypeTransformImpl.castObjectToString(tuples[aliasList.get("project")]));
        rs.setInvestor(TypeTransformImpl.castObjectToString(tuples[aliasList.get("investor")]));
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        rs.setTypeName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("typeName")]));
        rs.setBalconyDirection(TypeTransformImpl.castObjectToString(tuples[aliasList.get("balconyDirection")]));
        rs.setAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("avatar")]));
        rs.setLatitude((Double) tuples[aliasList.get("latitude")]);
        rs.setLongitude((Double) tuples[aliasList.get("longitude")]);
        rs.setJuridical(TypeTransformImpl.castObjectToString(tuples[aliasList.get("juridical")]));
        rs.setFurniture(TypeTransformImpl.castObjectToString(tuples[aliasList.get("furniture")]));
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
        rs.setFacilityId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("facilityId")]));
        rs.setFacilityName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("facilityName")]));
        rs.setFacilityTypeId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("facilityTypeId")]));
        rs.setFacilityTypeName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("facilityTypeName")]));
        rs.setLatitude((Double) tuples[aliasList.get("latitude")]);
        rs.setLongitude((Double) tuples[aliasList.get("longitude")]);
        rs.setDistance((Double) tuples[aliasList.get("distance")]);
        return StringUtils.isEmpty(rs.getFacilityId()) ? null : rs;
    }
}