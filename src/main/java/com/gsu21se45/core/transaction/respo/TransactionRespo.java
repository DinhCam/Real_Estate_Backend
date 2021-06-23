package com.gsu21se45.core.transaction.respo;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.core.transaction.transformer.TransactionByUserIdTransformer;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface TransactionRespo {
    boolean createTransaction(CTransactionDto transactionDto);
    Page<GTransactionDto> getTransactionByUserId(RequestPrams rq, Pageable p);

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

        @Override
        public Page<GTransactionDto> getTransactionByUserId(RequestPrams rq, Pageable p) {
            List<GTransactionDto> rs = (List<GTransactionDto>) em
                    .createNativeQuery(Query.getTransactionByUserId)
                    .setParameter("userId", rq.getUserId())
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new TransactionByUserIdTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }
    }
    class Query{
        public static final String CREATE_TRANSACTION = "insert into transaction (title, buyer_id, seller_id, staff_id, real_estate_id, down_price, create_at)\n" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        public static String getTransactionByUserId = "select tr.id as id, tr.title as title,\n" +
                "s.username as sellerName, b.username as buyerName,\n" +
                "st.username as staffName, tr.down_price as downPrice, tr.create_at as createAt\n" +
                "from transaction tr\n" +
                "left join user s on tr.seller_id = s.id\n" +
                "left join user b on tr.buyer_id = b.id\n" +
                "left join user st on tr.staff_id = st.id\n" +
                "where (tr.seller_id = :userId) or (tr.buyer_id = :userId) or (tr.staff_id = :userId)\n" +
                "order by tr.create_at DESC";
    }
}