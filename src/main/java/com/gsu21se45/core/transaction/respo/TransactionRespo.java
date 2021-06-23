package com.gsu21se45.core.transaction.respo;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GRealEstateAssignedStaffDto;
import com.gsu21se45.core.transaction.transformer.RealEstateAssignedStaffTransformer;
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
    Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams rq, Pageable p);

    @Repository
    class TransactionRespoImple implements  TransactionRespo{
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
        public Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams rq, Pageable p) {
            List<GRealEstateAssignedStaffDto> rs = (List<GRealEstateAssignedStaffDto>) em
                    .createNativeQuery(Query.getRealEstateAssignStaff)
                    .setParameter("staffId",rq.getStaffId())
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateAssignedStaffTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }
    }
    class Query{
        public static final String CREATE_TRANSACTION = "insert into transaction (title, buyer_id, seller_id, staff_id, real_estate_id, down_price, create_at)\n" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        public static String getRealEstateAssignStaff = "select r.id as realEstateId, \n" +
                "c.buyer_id as buyerId,\n" +
                "b.username as buyerName,\n" +
                "b.avatar as avatar,\n" +
                "s.username as sellerName,\n" +
                "st.username as staffName,\n" +
                "r.title as title,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName,\n" +
                "rd.area as area,\n" +
                "r.create_at as createAt,\n" +
                "rd.description as description,\n" +
                "rd.price as price\n" +
                "from real_estate r \n" +
                "left join conversation c on r.id = c.real_estate_id\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join user b on c.buyer_id = b.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where r.staff_id = :staffId";
    }
}