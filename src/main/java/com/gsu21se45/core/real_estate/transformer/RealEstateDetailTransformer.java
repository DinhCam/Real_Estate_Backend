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
                if( result.get(tuples[aliasList.get("id")]).getFacilities().containsKey(facilityDto.getFacilityTypeName())){
                    result.get(tuples[aliasList.get("id")]).getFacilities().get(facilityDto.getFacilityTypeName()).add(facilityDto);
                }else{
                    Set<FacilityDto> facilityDtos = new HashSet<>();
                    facilityDtos.add(facilityDto);
                    result.get(tuples[aliasList.get("id")]).getFacilities().put(facilityDto.getFacilityTypeName(),facilityDtos);
                }
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
        if(rs.getFacilities().containsKey(getFacilityDto(tuples,aliasList).getFacilityTypeName())){
            rs.getFacilities().get(getFacilityDto(tuples,aliasList).getFacilityTypeName()).add(getFacilityDto(tuples,aliasList));
        }
        else{
            Set<FacilityDto> facilityDtos = new LinkedHashSet<>();
            facilityDtos.add(getFacilityDto(tuples,aliasList));
            rs.getFacilities().put(getFacilityDto(tuples,aliasList).getFacilityTypeName(),facilityDtos);
        }
        Set<ImageDto> imgs = new HashSet<>();
        imgs.add(getImageDto(tuples,aliasList));
        rs.setImages(imgs);
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setDescription(TypeTransformImpl.castObjectToString(tuples[aliasList.get("description")]));
        rs.setView(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("view")]));
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerPhone")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setSellerAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerAvatar")]));
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffPhone")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setStaffAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffAvatar")]));
        rs.setDataentryId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryId")]));
        rs.setDataentryPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryPhone")]));
        rs.setDataentryName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryName")]));
        rs.setDataentryAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryAvatar")]));
        rs.setLength((Double) tuples[aliasList.get("length")]);
        rs.setWidth((Double) tuples[aliasList.get("width")]);
        rs.setArea((Double)tuples[aliasList.get("area")]);
        rs.setFloor(TypeTransformImpl.castObjectToString(tuples[aliasList.get("floor")]));
        rs.setPrice((Double)tuples[aliasList.get("price")]);
        rs.setDirection(TypeTransformImpl.castObjectToString(tuples[aliasList.get("direction")]));
        rs.setNumberOfBedroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBedroom")]));
        rs.setNumberOfBathroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBathroom")]));
        rs.setProject(TypeTransformImpl.castObjectToString(tuples[aliasList.get("project")]));
        rs.setInvestor(TypeTransformImpl.castObjectToString(tuples[aliasList.get("investor")]));
        rs.setRealEstateNo(TypeTransformImpl.castObjectToString(tuples[aliasList.get("realEstateNo")]));
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("wardId")]));
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("disId")]));
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        rs.setTypeId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("typeId")]));
        rs.setTypeName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("typeName")]));
        rs.setBalconyDirection(TypeTransformImpl.castObjectToString(tuples[aliasList.get("balconyDirection")]));
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
        rs.setLatitudeFacility((Double) tuples[aliasList.get("latitudeFacility")]);
        rs.setLongitudeFacility((Double) tuples[aliasList.get("longitudeFacility")]);
        rs.setAddressFacility(TypeTransformImpl.castObjectToString(tuples[aliasList.get("addressFacility")]));
        rs.setDistance((Double) tuples[aliasList.get("distance")]);
        return StringUtils.isEmpty(rs.getFacilityId()) ? null : rs;
    }
}