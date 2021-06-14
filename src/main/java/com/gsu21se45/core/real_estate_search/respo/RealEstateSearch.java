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
               "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "r.view as view, \n" +
                "s.fullname as sellerName, \n" +
                "st.fullname as staffName ,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "ft.name as facilityType,\n" +
                "f.id as facilityId,\n" +
                "f.name as facilityName,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.real_estate_id\n" +
                "left join image_resource i on rd.id = i.real_estate_id\n" +
                "left join seller s on r.id = s.id\n" +
                "left join staff st on r.id = st.id\n" +
                "left join real_estate_facility rf on rd.id = rf.real_estate_detail_id\n" +
                "left join facility f on rf.facility_id = f.id\n" +
                "left join facility_type ft on f.type_id = ft.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id";
    }
}

