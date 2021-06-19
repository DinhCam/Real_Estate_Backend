package com.gsu21se45.core.real_estate_search.respo;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate_search.dto.RealEstateDto;
import com.gsu21se45.core.real_estate_search.transformer.RealEstateTransformer;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.entity_tmp.RealEstate;
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
    boolean updateRealEstate(CTransactionDto transactionDto);
    @Repository
    class  RealEstateImpl implements RealEstateSearch {
        @Autowired
        private EntityManager em;
        @Override
        public Page<RealEstateDto> getRealEstates(RequestPrams rq, Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.findAllRealEstate)
                    .setParameter("price",rq.getPrice())
                    .setParameter("fromArea", rq.getFromArea())
                    .setParameter("toArea", rq.getToArea())
                    .setParameter("type", rq.getType())
                    .setParameter("search",'%'+ rq.getSearch()+ '%')
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public boolean updateRealEstate(CTransactionDto transactionDto) {
            try{
                RealEstate  realEstate =  em.find(RealEstate.class,transactionDto.getRealEstateId());
                realEstate.setStatus((byte) 0);
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    class Query{
        public static String findAllRealEstate = "select r.id as id, \n" +
                "rt.id as typeId, \n"+
                "r.title as title, \n" +
                "r.status, \n" +
                "rd.description as description,\n" +
                "r.view as view, \n" +
                "s.username as sellerName, \n" +
                "st.username as staffName ,\n" +
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
                "d.name as disName,\n" +
                "concat(street.name, ' ', w.name, ' ', d.name) as address\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.real_estate_id\n" +
                "left join real_estate_type rt on rd.type_id = rt.id \n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_facility rf on rd.id = rf.real_estate_detail_id\n" +
                "left join facility f on rf.facility_id = f.id\n" +
                "left join facility_type ft on f.type_id = ft.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "having r.status = 1 and " +
                "(:price is null or rd.price <= :price) " +
                "and (:fromArea is null or rd.area between :fromArea and :toArea) " +
                "and (:type is null or typeId = :type)\n" +
                "and(:search is null or  address like :search)\n" +
                "order by rd.id";
    }
}

