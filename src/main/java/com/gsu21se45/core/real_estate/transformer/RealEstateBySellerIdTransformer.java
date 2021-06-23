package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.FacilityDto;
import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.core.real_estate.dto.RealEstateDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.*;

public class RealEstateBySellerIdTransformer implements ResultTransformer {
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
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setArea((double)tuples[aliasList.get("area")]);
        rs.setPrice((double)tuples[aliasList.get("price")]);
        rs.setAveragePrice((Double) tuples[aliasList.get("averagePrice")]);
        rs.setDirection(TypeTransformImpl.castObjectToString(tuples[aliasList.get("direction")]));
        rs.setNumberOfBedroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBedroom")]));
        rs.setNumberOfBathroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBathroom")]));
        rs.setProject(TypeTransformImpl.castObjectToString(tuples[aliasList.get("project")]));
        rs.setInvestor(TypeTransformImpl.castObjectToString(tuples[aliasList.get("investor")]));
        rs.setImages(imgs);
        rs.setFacilities(facility);
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        rs.setTypeName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("typeName")]));
        rs.setBalconyDirection(TypeTransformImpl.castObjectToString(tuples[aliasList.get("balconyDirection")]));
        rs.setAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("avatar")]));
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
        rs.setDistance((Double) tuples[aliasList.get("distance")]);
        return rs;
    }
}
