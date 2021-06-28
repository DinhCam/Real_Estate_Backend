package com.gsu21se45.core.real_estate.respo;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.transformer.*;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.entity.*;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface RealEstateRespo {
    Page<RealEstateDto> getRealEstates(RequestPrams rq, Pageable p);
    Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams rq, Pageable p);
    Page<RealEstateDto> getRealEstatesBySellerId(RequestPrams rq, Pageable p);
    RealEstateDto getRealEstateById(int id);
    List<RealEstateTypeDto> getAllRealEstateType();
    List<AddressDto> getAddress();
    boolean updateRealEstateByCTransaction(CTransactionDto transactionDto);
    boolean createRealEstate(CRealEstate cRealEstate);

    @Repository
     class RealEstateRespoImpl  implements RealEstateRespo {
        @Autowired
        private EntityManager em;

        @Override
        public Page<RealEstateDto> getRealEstates(RequestPrams rq, Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.findAllRealEstate)
                    .setParameter("fromPrice",rq.getFromPrice())
                    .setParameter("toPrice", rq.getToPrice())
                    .setParameter("fromArea", rq.getFromArea())
                    .setParameter("toArea", rq.getToArea())
                    .setParameter("type", rq.getType())
                    .setParameter("address", rq.getAddress())
                    .setParameter("title",rq.getTitle())
                    .setParameter("project", rq.getProject())
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams rq, Pageable p) {
            List<GRealEstateAssignedStaffDto> rs = (List<GRealEstateAssignedStaffDto>) em
                    .createNativeQuery(RealEstateRespo.Query.getRealEstateAssignStaff)
                    .setParameter("staffId",rq.getStaffId())
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateAssignedStaffTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<RealEstateDto> getRealEstatesBySellerId(RequestPrams rq, Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstateBySellerId)
                    .setParameter("sellerId",rq.getSellerId())
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public RealEstateDto getRealEstateById(int id) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstateDetailById)
                    .setParameter("id", id)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return rs.get(0);
        }

        @Override
        public List<RealEstateTypeDto> getAllRealEstateType() {
            List<RealEstateTypeDto> rs = (List<RealEstateTypeDto>) em
                    .createNativeQuery(Query.getAllRealEstateType)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTypeTransformer())
                    .getResultList();
            return rs;
        }

        @Override
        public List<AddressDto> getAddress() {
            List<AddressDto> rs = (List<AddressDto>) em
                    .createNativeQuery(Query.getAddress)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new AddressTransformer())
                    .getResultList();
            return rs;
        }

        @Override
        public boolean updateRealEstateByCTransaction(CTransactionDto transactionDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatus)
                        .setParameter("id",transactionDto.getRealEstateId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean createRealEstate(CRealEstate cRealEstate) {
            Session session = em.unwrap(Session.class);
            RealEstate realEstate = new RealEstate();
            Integer id = 0;
            Street street = new Street();
            Integer streetId = 0;
            StreetWard streetWard = new StreetWard();
            Integer streetWardId = 0;
            RealEstateDetail realEstateDetail = new RealEstateDetail();
            try {
                java.sql.Timestamp  sqlDate = new java.sql.Timestamp (new java.util.Date().getTime());
                String status = "active";
                realEstate.setSeller(em.find(User.class,cRealEstate.getSellerId()));
                realEstate.setStaff(em.find(User.class,cRealEstate.getStaffId()));
                realEstate.setTitle(cRealEstate.getTitle());
                realEstate.setView(cRealEstate.getView());
                realEstate.setCreateAt(sqlDate);
                realEstate.setStatus(status);
                id = (Integer) session.save(realEstate);

                street.setName(cRealEstate.getStreetName());
                streetId = (Integer) session.save(street);

                streetWard.setStreet(em.find(Street.class, streetId));
                streetWard.setWard(em.find(Ward.class, cRealEstate.getWardId()));

                streetWardId = (Integer) session.save(streetWard);

                realEstateDetail.setId(id);
                realEstateDetail.setRealEstateNo(cRealEstate.getRealEstateNo());
                realEstateDetail.setStreetWard(em.find(StreetWard.class, streetWardId));
                realEstateDetail.setRealEstateType(em.find(RealEstateType.class,cRealEstate.getTypeId()));
                realEstateDetail.setDescription(cRealEstate.getDescription());
                realEstateDetail.setArea(cRealEstate.getArea());
                realEstateDetail.setPrice(cRealEstate.getPrice());
                realEstateDetail.setDirection(cRealEstate.getDirection());
                realEstateDetail.setBalconyDirection(cRealEstate.getBalconyDirection());
                realEstateDetail.setProject(cRealEstate.getProject());
                realEstateDetail.setInvestor(cRealEstate.getInvestor());
                realEstateDetail.setNumOfBedroom(cRealEstate.getNumberOfBedroom());
                realEstateDetail.setNumOfBathroom(cRealEstate.getNumberOfBathroom());
                realEstateDetail.setLot(cRealEstate.getLot());

                session.save(realEstateDetail);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }
    }

    class Query{
        public static String findAllRealEstate = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.type_id as typeId, \n" +
                "r.status as status, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.username as sellerName, \n" +
                "s.avatar as avatar, \n" +
                "st.id as staffId, \n" +
                "st.username as staffName ,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.direction as direction,\n" +
                "rd.balcony_direction as balconyDirection,\n" +
                "sw.average_price as averagePrice,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "rd.investor as investor,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "ft.name as facilityType,\n" +
                "f.id as facilityId,\n" +
                "f.name as facilityName,\n" +
                "rf.distance as distance,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName,\n" +
                "concat(street.name, ' ', w.name, ' ', d.name) as address\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
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
                "having r.status = 'active'\n" +
                "and ((:fromPrice is null) or (rd.price between :fromPrice and :toPrice))\n" +
                "and ((:fromArea is null) or (rd.area between :fromArea and :toArea))\n" +
                "and ((:type is null) or (typeId = :type))\n" +
                "and ((:address is null) or (address like concat('%', concat(:address, '%'))))\n" +
                "and ((:title is null) or  (title like concat('%', concat(:title, '%'))))\n" +
                "and ((:project is null) or  (project like concat('%', concat(:project, '%'))))\n" +
                "order by rd.id";

        public static String getRealEstateAssignStaff = "select r.id as id, \n" +
                "c.buyer_id as buyerId,\n" +
                "b.username as buyerName,\n" +
                "b.avatar as avatar,\n" +
                "s.id as sellerId, \n" +
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
                "where r.staff_id = :staffId\n" +
                "order by rd.id";

        public static String getRealEstateBySellerId = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.username as sellerName, \n" +
                "st.id as staffId,\n" +
                "st.username as staffName,\n" +
                "st.avatar as avatar,\n" +
                "rd.direction as direction,\n" +
                "rd.balcony_direction as balconyDirection,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "sw.average_price as averagePrice,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "rd.investor as investor,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "ft.name as facilityType,\n" +
                "f.id as facilityId,\n" +
                "f.name as facilityName,\n" +
                "rf.distance as distance,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_facility rf on rd.id = rf.real_estate_detail_id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join facility f on rf.facility_id = f.id\n" +
                "left join facility_type ft on f.type_id = ft.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where (s.id = :sellerId) \n" +
                "order by rd.id";

        public static String getRealEstateDetailById = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.username as sellerName, \n" +
                "st.id as staffId,\n" +
                "st.username as staffName,\n" +
                "st.avatar as avatar,\n" +
                "rd.direction as direction,\n" +
                "rd.balcony_direction as balconyDirection,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "sw.average_price as averagePrice,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "rd.investor as investor,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "ft.name as facilityType,\n" +
                "f.id as facilityId,\n" +
                "f.name as facilityName,\n" +
                "rf.distance as distance,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_facility rf on rd.id = rf.real_estate_detail_id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join facility f on rf.facility_id = f.id\n" +
                "left join facility_type ft on f.type_id = ft.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where rd.id = :id \n";

        public static String getAllRealEstateType = "select rt.id as id, rt.name as name\n" +
                "from real_estate_type rt";

        public static String getAddress = "select dis.id as id, \n" +
                "dis.name as disName, \n" +
                "w.id as wardId,\n" +
                "w.name as wardName\n" +
                "from district dis\n" +
                "left join ward w on dis.id = w.district_id";

        public static String updateRealEstateStatus = "update real_estate set status = 'sold' where id = :id";
    }
}