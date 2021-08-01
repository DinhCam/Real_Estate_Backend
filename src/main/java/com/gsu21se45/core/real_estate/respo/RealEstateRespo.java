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
import java.util.*;

public interface RealEstateRespo {
    Page<RealEstateDto> getAllRealEstates(RequestPrams rq, Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Pageable p);
//    Page<GRealEstateByCensorDto> getRealEstateByCensor(Pageable p);
    Page<RealEstateDto> getRealEstatesNotAssign(Pageable p);
    Page<RealEstateDto> getRealEstatesAssigned(Pageable p);
    Page<RealEstateActiveByStaffDto> getRealEstatesByStaff(String staffId, String status, Pageable p);
    RealEstateDetailDto getRealEstateDetailById(int id);
    Integer getNumberOfRealEstateByStaff(String staffId, String status);
    List<RealEstateTypeDto> getAllRealEstateType();
    List<StaffDto> getAllStaff();
    boolean updateRealEstateStatusByCTransaction(CTransactionDto transactionDto);
    void updateView(int id);
    boolean createRealEstate(CRealEstate cRealEstate);
    boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssign updateRealEstateByManagerAssign);
    boolean updateRealEstateStatus(UpdateStatus updateStatus);
    boolean updateRealEstateRejected(UpdateRejected updateRejected);

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
        public Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstateAssignStaff)
                    .setParameter("staffId", staffId)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstateBySeller)
                    .setParameter("sellerId", sellerId)
                    .setParameter("status", status)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstateActiveBySeller)
                    .setParameter("sellerId", sellerId)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

//        @Override
//        public Page<GRealEstateByCensorDto> getRealEstateByCensor(Pageable p) {
//            List<GRealEstateByCensorDto> rs = (List<GRealEstateByCensorDto>) em
//                    .createNativeQuery(Query.getRealEstateByCensor)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
//                    .unwrap(NativeQuery.class)
//                    .setResultTransformer(new RealEstateCensorTransformer())
//                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());
//        }

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
        public Page<RealEstateDto> getRealEstatesAssigned(Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstatesAssigned)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
            return new PageImpl<>(rs,p,rs.size());
        }

        @Override
        public Page<RealEstateActiveByStaffDto> getRealEstatesByStaff(String staffId, String status, Pageable p) {
            List<RealEstateActiveByStaffDto> rs = (List<RealEstateActiveByStaffDto>) em
                    .createNativeQuery(Query.getRealEstatesByStaff)
                    .setParameter("staffId", staffId)
                    .setParameter("status", status)
                    .setFirstResult((int) p.getOffset())
                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateActiveByStaffTransformer())
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
        public Integer getNumberOfRealEstateByStaff(String staffId, String status) {
            String rs = em.createNativeQuery(Query.getNumberOfRealEstateByStaff)
                    .setParameter("staffId", staffId)
                    .setParameter("status", status)
                    .getSingleResult().toString();
            return Integer.parseInt(rs);
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
        public List<StaffDto> getAllStaff() {
            List<StaffDto> rs = (List<StaffDto>) em
                    .createNativeQuery(Query.getAllStaff)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new StaffTransformer())
                    .getResultList();
            return rs;
        }

        @Override
        public boolean updateRealEstateStatusByCTransaction(CTransactionDto transactionDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatus)
                        .setParameter("id",transactionDto.getRealEstateId())
                        .setParameter("status", "sold")
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public void updateView(int id) {
            try{
                em.createNativeQuery(Query.updateView)
                        .setParameter("id",id)
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }
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
            Integer realEstateDetailId = 0;
            try {
                java.sql.Timestamp  sqlDate = new java.sql.Timestamp (new java.util.Date().getTime());
                String status = "inactive";
                realEstate.setSeller(em.find(User.class,cRealEstate.getSellerId()));
                realEstate.setTitle(cRealEstate.getTitle());
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
                realEstateDetail.setLatitude(cRealEstate.getLatitude());
                realEstateDetail.setLongitude(cRealEstate.getLongitude());
                realEstateDetail.setRealEstateType(em.find(RealEstateType.class,cRealEstate.getTypeId()));
                realEstateDetail.setDescription(cRealEstate.getDescription());
                realEstateDetail.setLength(cRealEstate.getLength());
                realEstateDetail.setWidth(cRealEstate.getWidth());
                realEstateDetail.setArea(cRealEstate.getArea());
                realEstateDetail.setPrice(cRealEstate.getPrice());
                realEstateDetail.setDirection(cRealEstate.getDirection());
                realEstateDetail.setBalconyDirection(cRealEstate.getBalconyDirection());
                realEstateDetail.setProject(cRealEstate.getProject());
                realEstateDetail.setInvestor(cRealEstate.getInvestor());
                realEstateDetail.setJuridical(cRealEstate.getJuridical());
                realEstateDetail.setFurniture(cRealEstate.getFurniture());
                realEstateDetail.setNumOfBedroom(cRealEstate.getNumberOfBedroom());
                realEstateDetail.setNumOfBathroom(cRealEstate.getNumberOfBathroom());

                realEstateDetailId = (Integer) session.save(realEstateDetail);

                for (ImageResource i:cRealEstate.getImages()) {
                    ImageResource imageResource = new ImageResource();
                    imageResource.setRealEstateDetail(em.find(RealEstateDetail.class, realEstateDetailId));
                    imageResource.setImgUrl(i.getImgUrl());
                    session.save(imageResource);
                }

                for (FacilityDto i:cRealEstate.getFacilities()) {
                    Facility facility = new Facility();
                    Integer facilityId = 0;
                    facility.setFacilityTypeId(em.find(FacilityType.class, i.getFacilityTypeId()));
                    facility.setFacilityName(i.getFacilityName());
                    facility.setLatitude(i.getLatitude());
                    facility.setLongitude(i.getLongitude());
                    facilityId = (Integer) session.save(facility);

                    RealEstateFacility realEstateFacility = new RealEstateFacility();
                    realEstateFacility.setRealEstateDetail(em.find(RealEstateDetail.class, realEstateDetailId));
                    realEstateFacility.setFacility(em.find(Facility.class, facilityId));
                    realEstateFacility.setDistance(i.getDistance());
                    session.save(facility);
                    session.save(realEstateFacility);
                }

            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssign updateRealEstateByManagerAssign) {
            try{
                em.createNativeQuery(Query.updateRealEstateByManagerAssign)
                        .setParameter("id", updateRealEstateByManagerAssign.getId())
                        .setParameter("staffId", updateRealEstateByManagerAssign.getStaffId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateStatus(UpdateStatus updateStatus) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatus)
                        .setParameter("id", updateStatus.getId())
                        .setParameter("status", updateStatus.getStatus())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateRejected(UpdateRejected updateRejected) {
            try{
                em.createNativeQuery(Query.updateRealEstateRejected)
                        .setParameter("id", updateRejected.getId())
                        .setParameter("reason", updateRejected.getReason())
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
                "s.fullname as sellerName, \n" +
                "s.avatar as sellerAvatar, \n" +
                "st.id as staffId, \n" +
                "st.fullname as staffName ,\n" +
                "st.avatar as staffAvatar, \n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName,\n" +
                "concat(street.name, ' ', w.name, ' ', d.name, ' ', r.title, ' ', rd.project) as search\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
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
                "and ((:disName is null) or (disName like concat('%', concat(:disName, '%'))))\n"+
                "order by r.view DESC";

        public static String getRealEstateAssignStaff = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "r.view as view, \n" +
                "r.status as status,\n" +
                "s.id as sellerId,\n" +
                "s.fullname as sellerName,\n" +
                "s.avatar as sellerAvatar,\n" +
                "c.buyer_id as buyerId,\n" +
                "b.fullname as buyerName,\n" +
                "b.avatar as buyerAvatar,\n" +
                "st.id as staffId,\n" +
                "st.fullname as staffName,\n" +
                "st.avatar as staffAvatar,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "r.reason as reason,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join conversation c on r.id = c.real_estate_id\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user b on c.buyer_id = b.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where (st.id = :staffId) \n" +
                "order by r.create_at DESC";

        public static String getRealEstateBySeller = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "r.view as view, \n" +
                "r.status as status,\n" +
                "s.id as sellerId,\n" +
                "s.fullname as sellerName,\n" +
                "s.avatar as sellerAvatar,\n" +
                "c.buyer_id as buyerId,\n" +
                "b.fullname as buyerName,\n" +
                "b.avatar as buyerAvatar,\n" +
                "st.id as staffId,\n" +
                "st.fullname as staffName,\n" +
                "st.avatar as staffAvatar,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "r.reason as reason, \n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName \n" +
                "from real_estate r\n" +
                "left join conversation c on r.id = c.real_estate_id\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user b on c.buyer_id = b.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where s.id = :sellerId \n" +
                "and r.status = :status \n" +
                "order by r.create_at DESC";

        public static String getRealEstateActiveBySeller = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "r.view as view, \n" +
                "r.status as status,\n" +
                "s.id as sellerId,\n" +
                "s.fullname as sellerName,\n" +
                "s.avatar as sellerAvatar,\n" +
                "c.buyer_id as buyerId,\n" +
                "b.fullname as buyerName,\n" +
                "b.avatar as buyerAvatar,\n" +
                "st.id as staffId,\n" +
                "st.fullname as staffName,\n" +
                "st.avatar as staffAvatar,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "r.reason as reason, \n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName \n" +
                "from real_estate r\n" +
                "left join conversation c on r.id = c.real_estate_id\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user b on c.buyer_id = b.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where s.id = :sellerId \n" +
                "and r.status = 'active' \n" +
                "order by r.view DESC";

        public static String getRealEstateDetailById = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.fullname as sellerName, \n" +
                "s.avatar as sellerAvatar,\n" +
                "st.id as staffId,\n" +
                "st.fullname as staffName,\n" +
                "st.avatar as staffAvatar,\n" +
                "rd.direction as direction,\n" +
                "rd.balcony_direction as balconyDirection,\n" +
                "rd.length as length,\n" +
                "rd.width as width,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "rd.investor as investor,\n" +
                "rd.latitude as latitude,\n" +
                "rd.longitude as longitude,\n" +
                "rd.juridical as juridical,\n" +
                "rd.furniture as furniture,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "ft.id as facilityTypeId,\n" +
                "ft.name as facilityTypeName,\n" +
                "f.id as facilityId,\n" +
                "f.name as facilityName,\n" +
                "rf.distance as distance,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
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
                "where rd.id = :id";

        public static String getRealEstatesNotAssign = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.fullname as sellerName, \n" +
                "s.avatar as sellerAvatar, \n" +
                "st.id as staffId, \n" +
                "st.fullname as staffName ,\n" +
                "st.avatar as staffAvatar, \n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where st.id is null \n" +
                "order by r.create_at DESC";

        public static String getRealEstatesAssigned = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.fullname as sellerName, \n" +
                "s.avatar as sellerAvatar, \n" +
                "st.id as staffId, \n" +
                "st.fullname as staffName ,\n" +
                "st.avatar as staffAvatar, \n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where st.id is not null \n" +
                "order by r.create_at DESC";

        public static String getRealEstatesByStaff = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "r.view as view, \n" +
                "r.status as status,\n" +
                "s.id as sellerId,\n" +
                "s.fullname as sellerName,\n" +
                "s.avatar as sellerAvatar,\n" +
                "r.buyer_id as buyerId,\n" +
                "b.fullname as buyerName,\n" +
                "b.avatar as buyerAvatar,\n" +
                "dl.offered_price as offeredPrice,\n" +
                "dl.create_at as createAtDeal,\n" +
                "st.id as staffId,\n" +
                "st.fullname as staffName,\n" +
                "st.avatar as staffAvatar,\n" +
                "rd.area as area,\n" +
                "rd.price as price,\n" +
                "r.reason as reason, \n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.name as wardName,\n" +
                "d.name as disName\n" +
                "from real_estate r\n" +
                "left join conversation c on r.id = c.real_estate_id\n" +
                "left join deal dl on c.id = dl.conversation_id and dl.status = 'accepted'\n" +
                "left join real_estate_detail rd on r.id = rd.id\n" +
                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
                "left join user b on c.buyer_id = b.id\n" +
                "left join user s on r.seller_id = s.id\n" +
                "left join user st on r.staff_id = st.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where (st.id = :staffId) \n" +
                "and (r.status = :status) \n" +
                "order by r.create_at DESC";

//        public static String getRealEstateByCensor = "select r.id as id, \n" +
//                "r.title as title, \n" +
//                "rd.description as description,\n" +
//                "r.view as view, \n" +
//                "r.status as status,\n" +
//                "s.id as sellerId,\n" +
//                "s.fullname as sellerName,\n" +
//                "s.avatar as sellerAvatar,\n" +
//                "rd.area as area,\n" +
//                "rd.price as price,\n" +
//                "rd.number_of_bedroom as numberOfBedroom,\n" +
//                "rd.number_of_bathroom as numberOfBathroom,\n" +
//                "rd.project as project,\n" +
//                "i.id as imgId,\n" +
//                "i.img_url as imageUrl,\n" +
//                "r.create_at as createAt,\n" +
//                "street.name as streetName,\n" +
//                "w.name as wardName,\n" +
//                "d.name as disName\n" +
//                "from real_estate r\n" +
//                "left join conversation c on r.id = c.real_estate_id\n" +
//                "left join real_estate_detail rd on r.id = rd.id\n" +
//                "left join image_resource i on rd.id = i.real_estate_detail_id\n" +
//                "left join user b on c.buyer_id = b.id\n" +
//                "left join user s on r.seller_id = s.id\n" +
//                "left join user st on r.staff_id = st.id\n" +
//                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
//                "left join street street on sw.street_id = street.id\n" +
//                "left join ward w on sw.ward_id = w.id\n" +
//                "left join district d on w.district_id = d.id\n" +
//                "where r.status = 'new' \n" +
//                "order by r.create_at DESC";

        public static String getNumberOfRealEstateByStaff = "select count(r.id) as numberOfRealEstate\n" +
                "from real_estate r\n" +
                "where staff_id = :staffId \n" +
                "and status = :status";

        public static String getAllRealEstateType = "select rt.id as id, rt.name as name\n" +
                "from real_estate_type rt";

        public static String getAllStaff = "select u.id as id, u.username as username, u.fullname as fullname, u.avatar as avatar\n" +
                "from user u\n" +
                "left join role r on u.role_id = r.id\n" +
                "where r.id = 3";

        public static String updateRealEstateStatus = "update real_estate set status = :status where id = :id";

        public static String updateRealEstateRejected = "update real_estate set status = 'rejected', reason = :reason where id = :id";

        public static String updateRealEstateByManagerAssign = "update real_estate set staff_id = :staffId where id = :id";

        public static String updateView = "update real_estate set view = view + 1 where id = :id";
    }
}