package com.gsu21se45.core.transaction.transformer;

import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.util.AliasHelper;
import com.gsu21se45.util.TypeTransformImpl;

import org.hibernate.transform.ResultTransformer;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        rs.setSellerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("sellerName")]));
        rs.setBuyerName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("buyerName")]));
        rs.setStaffName(TypeTransformImpl.castObjectToString(tuples[aliasList.get("staffName")]));
        rs.setTitle(TypeTransformImpl.castObjectToString(tuples[aliasList.get("title")]));
        rs.setDownPrice((Double) tuples[aliasList.get("downPrice")]);
        rs.setCreateAt((Timestamp) tuples[aliasList.get("createAt")]);
        return rs;
    }
}
