package com.gsu21se45.core.transaction.respo;

import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.core.transaction.transformer.TransactionByUserIdTransformer;
import com.gsu21se45.entity.ImageResource;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.entity.Transaction;
import com.gsu21se45.entity.User;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public interface TransactionRepository {
    boolean createTransaction(CTransactionDto transactionDto);
    Page<GTransactionDto> getTransactionByUserId(String userId, Pageable p);

    @Repository
    class TransactionRepositoryImpl implements TransactionRepository {
        @Autowired
        private EntityManager em;

        @Override
        public boolean createTransaction(CTransactionDto transactionDto) {
            Session session = em.unwrap(Session.class);
            Transaction transaction = new Transaction();
            Integer transactionId = 0;
            try{
                java.sql.Timestamp  sqlDate = new java.sql.Timestamp (new java.util.Date().getTime());
                transaction.setBuyer(em.find(User.class, transactionDto.getBuyerId()));
                transaction.setSeller(em.find(User.class, transactionDto.getSellerId()));
                transaction.setStaff(em.find(User.class, transactionDto.getStaffId()));
                transaction.setRealEstate(em.find(RealEstate.class, transactionDto.getRealEstateId()));
                transaction.setDownPrice(transactionDto.getDownPrice());
                transaction.setDeposit(transactionDto.getDeposit());
                transaction.setNote(transactionDto.getNote());
                transaction.setCreateAt(sqlDate);

                transactionId = (Integer) session.save(transaction);

                for (ImageResource i:transactionDto.getImages()){
                    ImageResource imageResource = new ImageResource();
                    imageResource.setTransaction(em.find(Transaction.class, transactionId));
                    imageResource.setImgUrl(i.getImgUrl());
                    session.save(imageResource);
                }

            } catch(Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public Page<GTransactionDto> getTransactionByUserId(String userId, Pageable p) {
            List<GTransactionDto> rs = (List<GTransactionDto>) em
                    .createNativeQuery(Query.getTransactionByUserId)
                    .setParameter("userId", userId)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new TransactionByUserIdTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GTransactionDto> content = new ArrayList<>();
            long index = p.getOffset();
            int s = content.size();
            while(content.size() < p.getPageSize()){
                if(p.getOffset() > rs.size()){
                    break;
                }
                if(index >= rs.size()){
                    break;
                }
                content.add(rs.get((int)index));
                index++;
            }
            return new PageImpl<>(content,p,rs.size());
        }
    }
    class Query{
        public static String getTransactionByUserId = "select distinct tr.id, \n" +
                "                s.id as sellerId,\n" +
                "                s.fullname as sellerName, \n" +
                "                b.id as buyerId,\n" +
                "                b.fullname as buyerName,\n" +
                "                st.id as staffId, \n" +
                "                st.fullname as staffName,\n" +
                "                r.id as realEstateId, \n" +
                "                r.title as realEstateTitle,\n" +
                "                street.name as streetName, \n" +
                "                w.name as wardName, \n" +
                "                d.name as disName,\n" +
                "                tr.down_price as downPrice, \n" +
                "                tr.deposit as deposit,\n" +
                "                tr.note as note, \n" +
                "                a.create_at as appointmentDate,\n" +
                "                i.id as imgId,\n" +
                "                i.img_url as imageUrl,\n" +
                "                tr.create_at as createAt\n" +
                "                from transaction tr\n" +
                "                left join user s on tr.seller_id = s.id\n" +
                "                left join user b on tr.buyer_id = b.id\n" +
                "                left join user st on tr.staff_id = st.id\n" +
                "                left join real_estate r on tr.real_estate_id = r.id\n" +
                "                left join image_resource i on tr.id = i.transaction_id\n" +
                "                left join conversation c on c.real_estate_id = r.id\n" +
                "                left join appointment a on a.conversation_id = c.id\n" +
                "                left join real_estate_detail rd on r.id = rd.id\n" +
                "                left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "                left join street street on sw.street_id = street.id\n" +
                "                left join ward w on sw.ward_id = w.id\n" +
                "                left join district d on w.district_id = d.id\n" +
                "                where (tr.seller_id = :userId) or (tr.buyer_id = :userId) or (tr.staff_id = :userId)\n" +
                "                order by tr.create_at DESC";
    }
}