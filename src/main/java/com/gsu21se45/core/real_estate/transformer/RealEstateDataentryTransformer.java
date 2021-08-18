package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.BuyerDto;
import com.gsu21se45.core.real_estate.dto.GRealEstateByDataentryDto;
import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

public class RealEstateDataentryTransformer implements ResultTransformer {
    Map<Integer, GRealEstateByDataentryDto> result = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            BuyerDto buyer = getBuyerDto(tuples,aliasList);
            if (buyer != null) {
                result.get(tuples[aliasList.get("id")]).getBuyers().add(buyer);
            }

            ImageDto img = getImageDto(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getImages().add(img);
        }
        else{
            GRealEstateByDataentryDto gRealEstateByDataentryDto = getRealEstateByDataentry(tuples, aliasList);
            result.put((Integer) tuples[aliasList.get("id")], gRealEstateByDataentryDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private GRealEstateByDataentryDto getRealEstateByDataentry(Object[] tuples, Map<String, Integer> aliasList){
        GRealEstateByDataentryDto rs = new GRealEstateByDataentryDto();
        if (getBuyerDto(tuples, aliasList) != null){
            rs.getBuyers().add(getBuyerDto(tuples, aliasList));
        }
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
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setStaffAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffAvatar")]));
        rs.setDataentryId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryId")]));
        rs.setDataentryName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryName")]));
        rs.setDataentryAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("dataentryAvatar")]));
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

    private BuyerDto getBuyerDto(Object[] tuples, Map<String, Integer> aliasList){
        BuyerDto  rs = new BuyerDto();
        rs.setBuyerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerId")]));
        rs.setBuyerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerName")]));
        rs.setBuyerAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerAvatar")]));
        return StringUtils.isEmpty(rs.getBuyerId()) ? null : rs;
    }

    private ImageDto getImageDto(Object[] tuples, Map<String, Integer> aliasList){
        ImageDto  rs = new ImageDto();
        rs.setImgId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("imgId")]));
        rs.setImgUrl(TypeTransformImpl.castObjectToString(tuples[aliasList.get("imageUrl")]));
        return rs;
    }
}
