package com.gsu21se45.core.real_estate.transformer;

import com.gsu21se45.core.real_estate.dto.BuyerDto;
import com.gsu21se45.core.real_estate.dto.GRealEstateAssignedStaffDto;
import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

public class RealEstateAssignedStaffTransformer implements ResultTransformer {
    Map<Integer, GRealEstateAssignedStaffDto> result = new HashMap<>();

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
            GRealEstateAssignedStaffDto gRealEstateAssignedStaffDto= getRealEstateAssignedStaffDto(tuples, aliasList);
            result.put((Integer) tuples[aliasList.get("id")],gRealEstateAssignedStaffDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private GRealEstateAssignedStaffDto getRealEstateAssignedStaffDto(Object[] tuples, Map<String, Integer> aliasList){
        GRealEstateAssignedStaffDto rs = new GRealEstateAssignedStaffDto();
        if (getBuyerDto(tuples, aliasList) != null){
            rs.getBuyers().add(getBuyerDto(tuples, aliasList));
        }
        Set<ImageDto> imgs = new HashSet<>();
        imgs.add(getImageDto(tuples,aliasList));
        rs.setImages(imgs);
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setStreetName((String) tuples[aliasList.get("streetName")]);
        rs.setWardName((String) tuples[aliasList.get("wardName")]);
        rs.setDisName((String)tuples[aliasList.get("disName")]);
        rs.setArea((Double) tuples[aliasList.get("area")]);
        rs.setPrice((Double) tuples[aliasList.get("price")]);
        rs.setDescription(TypeTransformImpl.castObjectToString(tuples[aliasList.get("description")]));
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        return rs;
    }

    private BuyerDto getBuyerDto(Object[] tuples, Map<String, Integer> aliasList){
        BuyerDto  rs = new BuyerDto();
        rs.setBuyerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerId")]));
        rs.setBuyerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerName")]));
        rs.setAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("avatar")]));
        return StringUtils.isEmpty(rs.getBuyerId()) ? null : rs;
    }

    private ImageDto getImageDto(Object[] tuples, Map<String, Integer> aliasList){
        ImageDto  rs = new ImageDto();
        rs.setImgId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("imgId")]));
        rs.setImgUrl(TypeTransformImpl.castObjectToString(tuples[aliasList.get("imageUrl")]));
        return rs;
    }
}