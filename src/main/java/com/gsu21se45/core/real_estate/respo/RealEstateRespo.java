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
    Page<RealEstateDto> getAllRealEstates(RequestPrams rq, Pageable p);
    Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(String staffId, Pageable p);
    Page<RealEstateDto> getRealEstatesBySellerId(String sellerId, Pageable p);
    Page<RealEstateDto> getRealEstatesNotAssign(Pageable p);
    Page<RealEstateDto> getRealEstatesInactive(Pageable p);
    RealEstateDetailDto getRealEstateDetailById(int id);
    List<RealEstateTypeDto> getAllRealEstateType();
    boolean updateRealEstateStatusByCTransaction(CTransactionDto transactionDto);
    boolean createRealEstate(CRealEstate cRealEstate);
    boolean updateRealEstateStatusByStaffAccuracy(UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy);
    boolean updateRealEstateByStaffAssign(UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy);
    boolean updateRealEstateStatusBySellerCancel(UpdateStatusBySellerCancel updateStatusBySellerCancel);

    @Repository
     class RealEstateRespoImpl  implements RealEstateRespo {
        @Autowired
        private EntityManager em;

        @Override
        public Page<RealEstateDto> getAllRealEstates(RequestPrams rq, Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getAllRealEstates)
                    .setParameter("minPrice",rq.getMinPrice())
                    .setParameter("maxPrice", rq.getMaxPrice())
                    .setParameter("minArea", rq.getMinArea())
                    .setParameter("maxArea", rq.getMaxArea())
                    .setParameter("type", rq.getType())
                    .setParameter("search", rq.getSearch())
                    .setParameter("disName", rq.getDisName())
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(String staffId, Pageable p) {
            List<GRealEstateAssignedStaffDto> rs = (List<GRealEstateAssignedStaffDto>) em
                    .createNativeQuery(RealEstateRespo.Query.getRealEstateAssignStaff)
                    .setParameter("staffId", staffId)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateAssignedStaffTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<RealEstateDto> getRealEstatesBySellerId(String sellerId, Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstateBySellerId)
                    .setParameter("sellerId", sellerId)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<RealEstateDto> getRealEstatesNotAssign(Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstatesNotAssign)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<RealEstateDto> getRealEstatesInactive(Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstatesInactive)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public RealEstateDetailDto getRealEstateDetailById(int id) {
            List<RealEstateDetailDto> rs = (List<RealEstateDetailDto>) em
                    .createNativeQuery(Query.getRealEstateDetailById)
                    .setParameter("id", id)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateDetailTransformer())
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
        public boolean updateRealEstateStatusByCTransaction(CTransactionDto transactionDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatusByCTransaction)
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
                String status = "inactive";
                realEstate.setSeller(em.find(User.class,cRealEstate.getSellerId()));
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

        @Override
        public boolean updateRealEstateStatusByStaffAccuracy(UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatusByStaffAccuracy)
                        .setParameter("id", updateStatusByStaffAccuracy.getId())
                        .setParameter("staffId", updateStatusByStaffAccuracy.getStaffId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateByStaffAssign(UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy) {
            try{
                em.createNativeQuery(Query.updateRealEstateByStaffAssign)
                        .setParameter("id", updateStatusByStaffAccuracy.getId())
                        .setParameter("staffId", updateStatusByStaffAccuracy.getStaffId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateStatusBySellerCancel(UpdateStatusBySellerCancel updateStatusBySellerCancel) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatusBySellerCancel)
                        .setParameter("id", updateStatusBySellerCancel.getId())
                        .setParameter("sellerId", updateStatusBySellerCancel.getSellerId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    class Query{
        public static String getAllRealEstates = "select r.id as id, \n" +
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
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "r.create_at as createAt,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName,\n" +
                "concat(street.name, ' ', w.name, ' ', d.name, ' ', r.title, ' ', rd.project) as search\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join real_estate_type rt on rd.type_id = rt.id \n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +

                "having r.status = 'active'\n" +

                "and ((:minPrice is null and :maxPrice is null) or " +
                "((:minPrice is not null and :maxPrice is not null) and (rd.price between :minPrice and :maxPrice)) or " +
                "((:minPrice is null) and (rd.price <= :maxPrice)) or " +
                "((:maxPrice is null) and (rd.price >= :minPrice)))\n" +

                "and ((:minArea is null and :maxArea is null) or " +
                "((:minArea is not null and :maxArea is not null) and (rd.area between :minArea and :maxArea)) or " +
                "((:minArea is null) and (rd.area <= :maxArea)) or " +
                "((:maxArea is null) and (rd.area >= :minArea)))\n" +

                "and ((:type is null) or (typeId = :type))\n" +
                "and ((:search is null) or (search like concat('%', concat(:search, '%'))))\n" +
                "and ((:disName is null) or (disName like concat('%', concat(:disName, '%'))))\n" +
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
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "r.create_at as createAt,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
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
                "where rd.id = :id \n" +
                "order by rd.id";

        public static String getRealEstatesNotAssign = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.username as sellerName, \n" +
                "s.avatar as avatar,\n" +
                "st.id as staffId,\n" +
                "st.username as staffName,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "r.create_at as createAt,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where st.id is null \n" +
                "order by rd.id";

        public static String getRealEstatesInactive = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.username as sellerName, \n" +
                "s.avatar as avatar,\n" +
                "st.id as staffId,\n" +
                "st.username as staffName,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "r.create_at as createAt,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where r.status = 'inactive' \n" +
                "order by rd.id";

        public static String getAllRealEstateType = "select rt.id as id, rt.name as name\n" +
                "from real_estate_type rt";

        public static String updateRealEstateStatusByCTransaction = "update real_estate set status = 'sold' where id = :id";

        public static String updateRealEstateStatusByStaffAccuracy = "update real_estate set status = 'active' where id = :id and staff_id = :staffId";

        public static String updateRealEstateByStaffAssign = "update real_estate set staff_id = :staffId where id = :id";

        public static String updateRealEstateStatusBySellerCancel = "update real_estate set status = 'cancel' where id = :id and seller_id = :sellerId";
    }
}