package com.gsu21se45.core.transaction.respo;

import com.gsu21se45.core.transaction.dto.CTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

public interface TransactionRespo {
    boolean createTransaction(CTransactionDto transactionDto);

    @Repository
    class TransactionRespoImple implements  TransactionRespo{
        @Autowired
        private EntityManager em;
        @Override
        public boolean createTransaction(CTransactionDto transactionDto) {
            try{
                em.createNativeQuery(Query.CREATE_TRANSACTION)
                        .setParameter(1,transactionDto.getBuyerId())
                        .setParameter(2,transactionDto.getSellerId())
                        .setParameter(3,transactionDto.getStaffId())
                        .setParameter(4,transactionDto.getRealEstateId())
                        .setParameter(5,transactionDto.getRealEstateId())
                        .setParameter(6,transactionDto.getCreateAt())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    class Query{
        static final String CREATE_TRANSACTION = "insert into transaction (buyer_id, seller_id, staff_id, real_estate_id, down_price, create_at)\n" +
                "values (?, ?, ?, ?, ?, ?)";
    }
}

