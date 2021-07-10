package com.gsu21se45.core.transaction.transformer;

import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;

import org.hibernate.transform.ResultTransformer;

import java.sql.Timestamp;
import java.util.*;

public class TransactionByUserIdTransformer implements ResultTransformer {
    private GTransactionDto rs = new GTransactionDto();

    @Override
    public Object transformTuple(Object[] tuples, String[] alias) {
        Map<String, Integer> aliasList = AliasHelper.toMap(alias);
        rs = getGTransactionDto(tuples, aliasList);
        return rs;
    }

    @Override
    public List transformList(List list) {
        return list;
    }

    private GTransactionDto getGTransactionDto(Object[] tuples, Map<String, Integer> aliasList){
        GTransactionDto rs = new GTransactionDto();
        rs.setId(TypeTransformImpl.castObjectToInt(tuples[aliasList.get("id")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
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
        rs.setDownPrice((Double) tuples[aliasList.get("downPrice")]);
        rs.setNote(TypeTransformImpl.castObjectToString(tuples[aliasList.get("note")]));
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        return rs;
    }
}
