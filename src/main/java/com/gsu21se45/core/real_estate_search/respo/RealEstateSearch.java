package com.gsu21se45.core.real_estate_search.respo;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate_search.dto.RealEstateDto;
import com.gsu21se45.core.real_estate_search.transformer.RealEstateTransformer;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface RealEstateSearch {
    Page<RealEstateDto> getRealEstates(RequestPrams rq, Pageable p);

    @Repository
    class  RealEstateImpl implements RealEstateSearch {
        @Autowired
        private EntityManager em;
        @Override
        public Page<RealEstateDto> getRealEstates(RequestPrams rq, Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.findAllRealEstate)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }
    }

    class Query{
        public static String findAllRealEstate = "with \n" +
                "seller as(select r.id, up.fullname from real_estate r join user u on r.seller_id = u.id\n" +
                "join user_profile up on u.profile_id = up.id\n" +
                "order by r.id\n" +
                "),\n" +
                "staff as (select r.id, up.fullname from real_estate r join user u on r.staff_id = u.id\n" +
                "join user_profile up on u.profile_id = up.id\n" +
                "order by r.id\n" +
                ")\n" +
                "select r.title as title, r.view as view, s.fullname as sellerName, st.fullname as staffName from real_estate r\n" +
                "join seller s on r.id = s.id\n" +
                "join staff st on r.id = st.id" ;
    }
}

