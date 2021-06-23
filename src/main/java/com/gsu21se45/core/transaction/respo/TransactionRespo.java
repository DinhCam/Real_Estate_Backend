package com.gsu21se45.core.transaction.respo;

import com.gsu21se45.core.transaction.dto.CTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

public interface TransactionRespo {
    boolean createTransaction(CTransactionDto transactionDto);

    @Repository
    class TransactionRespoImpl implements  TransactionRespo{
        @Autowired
        private EntityManager em;

        @Override
        public boolean createTransaction(CTransactionDto transactionDto) {
            try{
                java.sql.Timestamp  sqlDate = new java.sql.Timestamp (new java.util.Date().getTime());
                em.createNativeQuery(Query.CREATE_TRANSACTION)
                        .setParameter(1, transactionDto.getTitle())
                        .setParameter(2, transactionDto.getBuyerId())
                        .setParameter(3, transactionDto.getSellerId())
                        .setParameter(4, transactionDto.getStaffId())
                        .setParameter(5, transactionDto.getRealEstateId())
                        .setParameter(6, transactionDto.getDownPrice())
                        .setParameter(7, sqlDate)
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    class Query{
        public static final String CREATE_TRANSACTION = "insert into transaction (title, buyer_id, seller_id, staff_id, real_estate_id, down_price, create_at)\n" +
                "values (?, ?, ?, ?, ?, ?, ?)";
    }
}