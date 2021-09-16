package com.gsu21se45.core.transaction.transformer;

import com.gsu21se45.core.real_estate.dto.ImageDto;
import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;

import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.*;

public class TransactionByUserIdTransformer implements ResultTransformer {
    Map<Integer, GTransactionDto> result = new LinkedHashMap<>();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);

        if(result.containsKey(tuples[aliasList.get("id")])){
            ImageDto img = getImageDto(tuples,aliasList);
            result.get(tuples[aliasList.get("id")]).getImages().add(img);
        }
        else{
            GTransactionDto gTransactionDto = getGTransactionDto(tuples,aliasList);
            result.put((Integer) tuples[aliasList.get("id")], gTransactionDto);
        }
        return null;
    }

    @Override
    public List transformList(List list) {
        return new ArrayList(result.values());
    }

    private GTransactionDto getGTransactionDto(Object[] tuples, Map<String, Integer> aliasList){
        GTransactionDto rs = new GTransactionDto();
        Set<ImageDto> imgs = new HashSet<>();
        imgs.add(getImageDto(tuples,aliasList));
        rs.setImages(imgs);
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setSellerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerId")]));
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setBuyerId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerId")]));
        rs.setBuyerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerName")]));
        rs.setStaffId(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffId")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setRealEstateId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("realEstateId")]));
        rs.setRealEstateTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("realEstateTitle")]));
        rs.setStreetName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("streetName")]));
        rs.setWardName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("wardName")]));
        rs.setDisName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("disName")]));
        rs.setDealPrice((Double) tuples[aliasList.get("dealPrice")]);
        rs.setNote(TypeTransformImpl.castObjectToString(tuples[aliasList.get("note")]));
        rs.setReason(TypeTransformImpl.castObjectToString(tuples[aliasList.get("reason")]));
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
