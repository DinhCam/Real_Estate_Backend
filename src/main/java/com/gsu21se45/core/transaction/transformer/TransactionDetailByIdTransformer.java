package com.gsu21se45.core.transaction.transformer;

import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.core.transaction.dto.GTransactionDetailDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;
import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.*;

public class TransactionDetailByIdTransformer implements ResultTransformer {
    Map<Integer, GTransactionDetailDto> result = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            ImageDto img = getImageDto(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getImages().add(img);
        }
        else{
            GTransactionDetailDto gTransactionDetailDto = getGTransactionDetailDto(tuples,aliasList);
            result.put((Integer) tuples[aliasList.get("id")], gTransactionDetailDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private GTransactionDetailDto getGTransactionDetailDto(Object[] tuples, Map<String, Integer> aliasList){
        GTransactionDetailDto rs = new GTransactionDetailDto();
        Set<ImageDto> imgs = new HashSet<>();
        imgs.add(getImageDto(tuples,aliasList));
        rs.setImages(imgs);
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setSellerAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerAvatar")]));
        rs.setSellerPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerPhone")]));
        rs.setSellerEmail(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerEmail")]));
        rs.setBuyerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerId")]));
        rs.setBuyerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerName")]));
        rs.setBuyerAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerAvatar")]));
        rs.setBuyerPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerPhone")]));
        rs.setBuyerEmail(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerEmail")]));
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setStaffAvatar(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffAvatar")]));
        rs.setStaffPhone(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffPhone")]));
        rs.setStaffEmail(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffEmail")]));
        rs.setRealEstateId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("realEstateId")]));
        rs.setRealEstateTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("realEstateTitle")]));
        rs.setStreetName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("streetName")]));
        rs.setWardName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("wardName")]));
        rs.setDisName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("disName")]));
        rs.setDealPrice((Double) tuples[aliasList.get("dealPrice")]);
        rs.setNote(TypeTransformImpl.castObjectToString(tuples[aliasList.get("note")]));
        rs.setAppointmentDate((Timestamp) tuples[aliasList.get("appointmentDate")]);
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
