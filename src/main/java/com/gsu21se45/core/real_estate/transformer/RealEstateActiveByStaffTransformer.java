package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.core.real_estate.dto.RealEstateActiveByStaffDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.*;

public class RealEstateActiveByStaffTransformer implements ResultTransformer {
    Map<Integer, RealEstateActiveByStaffDto> result = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            ImageDto img = getImageDto(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getImages().add(img);
        }
        else{
            RealEstateActiveByStaffDto realEstateActiveByStaffDto = realEstateActiveByStaff(tuples, aliasList);
            result.put((Integer) tuples[aliasList.get("id")], realEstateActiveByStaffDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private RealEstateActiveByStaffDto realEstateActiveByStaff(Object[] tuples, Map<String, Integer> aliasList){
        RealEstateActiveByStaffDto rs = new RealEstateActiveByStaffDto();
        Set<ImageDto> imgs = new HashSet<>();
        imgs.add(getImageDto(tuples,aliasList));
        rs.setImages(imgs);
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setDescription(TypeTransformImpl.castObjectToString(tuples[aliasList.get("description")]));
        rs.setView(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("view")]));
        rs.setStatus(TypeTransformImpl.castObjectToString(tuples[aliasList.get("status")]));
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setSellerAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerAvatar")]));
        rs.setBuyerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerId")]));
        rs.setBuyerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerName")]));
        rs.setBuyerAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerAvatar")]));
        rs.setOfferedPrice((Double) tuples[aliasList.get("offeredPrice")]);
        rs.setCreateAtDeal((Timestamp) tuples[aliasList.get("createAtDeal")]);
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setStaffAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffAvatar")]));
        rs.setArea((Double) tuples[aliasList.get("area")]);
        rs.setPrice((Double) tuples[aliasList.get("price")]);
        rs.setReason((String) tuples[aliasList.get("reason")]);
        rs.setNumberOfBathroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBathroom")]));
        rs.setNumberOfBedroom(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("numberOfBedroom")]));
        rs.setProject(TypeTransformImpl.castObjectToString(tuples[aliasList.get("project")]));
        rs.setRealEstateNo(TypeTransformImpl.castObjectToString(tuples[aliasList.get("realEstateNo")]));
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
}
