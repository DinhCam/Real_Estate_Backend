package com.gsu21se45.core.real_estate.repository;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.transformer.*;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.entity.*;
import com.gsu21se45.util.filterHelper.SortBuilder;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.util.*;

public interface RealEstateRepository {
    Page<RealEstateDto> getRealEstate(RequestPrams rq, Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Pageable p);
    Page<GRealEstateByDataentryDto> getRealEstatesByDataentry(String dataentryId, String status, Pageable p);
    Page<GRealEstateByDataentryDto> getRealEstatesNotVerifyByDataentry(Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Pageable p);
    Page<RealEstateDto> getRealEstatesNotAssign(Pageable p);
    Page<RealEstateDto> getRealEstatesAssigned(Pageable p);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesByStaff(String staffId, String status, Pageable p);
    RealEstateDetailDto getRealEstateDetailById(int id);
    Integer getNumberOfRealEstateByStaff(String staffId, String status);
    List<RealEstateTypeDto> getAllRealEstateType();
    List<StaffDto> getAllStaff(String phone, String fullname);
    boolean updateRealEstateStatusByCTransaction(CTransactionDto transactionDto);
    void updateView(int id);
    boolean createRealEstate(CRealEstateDto cRealEstateDto);
    boolean updateRealEstate(UpdateRealEstateDto updateRealEstateDto);
    boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssignDto updateRealEstateByManagerAssignDto);
    boolean updateRealEstateStatus(UpdateStatusDto updateStatusDto);
    boolean updateRealEstateDetailLatLng(UpdateLatLngDto updateLatLngDto);
    boolean updateBuyerId(UpdateBuyerIdDto updateBuyerIdDto);
    boolean updateRealEstateRejected(UpdateRejectedDto updateRejectedDto);

    @Repository
     class RealEstateRepositoryImpl implements RealEstateRepository {
        @Autowired
        private EntityManager em;

        @Autowired
        RestTemplate restTemplate;

        @Override
        public Page<RealEstateDto> getRealEstate(RequestPrams rq, Pageable p) {
            StringBuilder getAllRealEstatesBuilder = new StringBuilder(Query.getRealEstate);
            SortBuilder sortBuilder = new SortBuilder();
            sortBuilder.buildOrder(getAllRealEstatesBuilder, p);

            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(getAllRealEstatesBuilder.toString())
                    .setParameter("minPrice", rq.getMinPrice())
                    .setParameter("maxPrice", rq.getMaxPrice())
                    .setParameter("minArea", rq.getMinArea())
                    .setParameter("maxArea", rq.getMaxArea())
                    .setParameter("type", rq.getType())
                    .setParameter("search", rq.getSearch())
                    .setParameter("disId", rq.getDisId())
                    .setParameter("wardId", rq.getWardId())
                    .setParameter("direction", rq.getDirection())
                    .setParameter("numberOfBedroom", rq.getNumberOfBedroom())
                    .setParameter("numberOfBathroom", rq.getNumberOfBathroom())
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
//            return new PageImpl<>(rs, p, rs.size());

            List<RealEstateDto> content = new ArrayList<>();
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

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstateAssignStaff)
                    .setParameter("staffId", staffId)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GRealEstateBySellerOrStaffDto> content = new ArrayList<>();
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

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstateBySeller)
                    .setParameter("sellerId", sellerId)
                    .setParameter("status", status)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GRealEstateBySellerOrStaffDto> content = new ArrayList<>();
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

        @Override
        public Page<GRealEstateByDataentryDto> getRealEstatesByDataentry(String dataentryId, String status, Pageable p) {
            List<GRealEstateByDataentryDto> rs = (List<GRealEstateByDataentryDto>) em
                    .createNativeQuery(Query.getRealEstateByDataentry)
                    .setParameter("dataentryId", dataentryId)
                    .setParameter("status", status)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateDataentryTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GRealEstateByDataentryDto> content = new ArrayList<>();
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

        @Override
        public Page<GRealEstateByDataentryDto> getRealEstatesNotVerifyByDataentry(Pageable p) {
            List<GRealEstateByDataentryDto> rs = (List<GRealEstateByDataentryDto>) em
                    .createNativeQuery(Query.getRealEstatesNotVerifyByDataentry)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateNotVerifyByDataentryTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GRealEstateByDataentryDto> content = new ArrayList<>();
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

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstateActiveBySeller)
                    .setParameter("sellerId", sellerId)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GRealEstateBySellerOrStaffDto> content = new ArrayList<>();
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

        @Override
        public Page<RealEstateDto> getRealEstatesNotAssign(Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstatesNotAssign)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<RealEstateDto> content = new ArrayList<>();
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

        @Override
        public Page<RealEstateDto> getRealEstatesAssigned(Pageable p) {
            List<RealEstateDto> rs = (List<RealEstateDto>) em
                    .createNativeQuery(Query.getRealEstatesAssigned)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<RealEstateDto> content = new ArrayList<>();
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

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesByStaff(String staffId, String status, Pageable p) {
            List<GRealEstateBySellerOrStaffDto> rs = (List<GRealEstateBySellerOrStaffDto>) em
                    .createNativeQuery(Query.getRealEstatesByStaff)
                    .setParameter("staffId", staffId)
                    .setParameter("status", status)
//                    .setFirstResult((int) p.getOffset())
//                    .setMaxResults(p.getPageSize())
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new RealEstateSellerOrStaffTransformer())
                    .getResultList();
//            return new PageImpl<>(rs,p,rs.size());

            List<GRealEstateBySellerOrStaffDto> content = new ArrayList<>();
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
        public List<StaffDto> getAllStaff(String phone, String fullname) {
            List<StaffDto> rs = (List<StaffDto>) em
                    .createNativeQuery(Query.getAllStaff)
                    .setParameter("phone", phone)
                    .setParameter("fullname", fullname)
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
        public boolean createRealEstate(CRealEstateDto cRealEstateDto) {
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
                if (!StringUtils.isEmpty(cRealEstateDto.getSellerId())) {
                    realEstate.setSeller(em.find(User.class, cRealEstateDto.getSellerId()));
                }
                if (!StringUtils.isEmpty(cRealEstateDto.getDataentryId())){
                    realEstate.setDataentry(em.find(User.class, cRealEstateDto.getDataentryId()));
                }
                realEstate.setTitle(cRealEstateDto.getTitle());
                realEstate.setCreateAt(sqlDate);
                realEstate.setStatus(status);
                id = (Integer) session.save(realEstate);

                street.setName(cRealEstateDto.getStreetName());
                streetId = (Integer) session.save(street);

                streetWard.setStreet(em.find(Street.class, streetId));
                streetWard.setWard(em.find(Ward.class, cRealEstateDto.getWardId()));

                streetWardId = (Integer) session.save(streetWard);

                realEstateDetail.setId(id);
                realEstateDetail.setRealEstateNo(cRealEstateDto.getRealEstateNo());
                realEstateDetail.setStreetWard(em.find(StreetWard.class, streetWardId));

                String address = cRealEstateDto.getAddress();

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}";
                Map<String, String> uriVariables = new HashMap<>();
                uriVariables.put("address", address);
                uriVariables.put("key", "AIzaSyAk_HxKWrfBT1g9WkfL0gqRIa9HD0d7Q0I");
                WrapperGeometryDto response =  restTemplate.getForObject(url, WrapperGeometryDto.class, uriVariables);

                realEstateDetail.setLatitude(response.getResults().get(0).getGeometry().getLocation().getLat());
                realEstateDetail.setLongitude(response.getResults().get(0).getGeometry().getLocation().getLng());

                realEstateDetail.setRealEstateType(em.find(RealEstateType.class, cRealEstateDto.getTypeId()));
                realEstateDetail.setDescription(cRealEstateDto.getDescription());
                realEstateDetail.setLength(cRealEstateDto.getLength());
                realEstateDetail.setWidth(cRealEstateDto.getWidth());
                realEstateDetail.setArea(cRealEstateDto.getArea());
                realEstateDetail.setFloor(cRealEstateDto.getFloor());
                realEstateDetail.setPrice(cRealEstateDto.getPrice());
                realEstateDetail.setDirection(cRealEstateDto.getDirection());
                realEstateDetail.setBalconyDirection(cRealEstateDto.getBalconyDirection());
                realEstateDetail.setProject(cRealEstateDto.getProject());
                realEstateDetail.setInvestor(cRealEstateDto.getInvestor());
                realEstateDetail.setJuridical(cRealEstateDto.getJuridical());
                realEstateDetail.setFurniture(cRealEstateDto.getFurniture());
                realEstateDetail.setNumOfBedroom(cRealEstateDto.getNumberOfBedroom());
                realEstateDetail.setNumOfBathroom(cRealEstateDto.getNumberOfBathroom());

                realEstateDetailId = (Integer) session.save(realEstateDetail);

                for (ImageResource i: cRealEstateDto.getImages()) {
                    ImageResource imageResource = new ImageResource();
                    imageResource.setRealEstateDetail(em.find(RealEstateDetail.class, realEstateDetailId));
                    imageResource.setImgUrl(i.getImgUrl());
                    session.save(imageResource);
                }

                String locationRealEstate = Double.toString(realEstateDetail.getLatitude()).concat(", ").concat(Double.toString(realEstateDetail.getLongitude()));

                for (int i = 1; i <= 5; i++){
                    String type = null;
                    if (i == 1) {
                        type = "school";
                    }
                    if (i == 2) {
                        type = "hospital";
                    }
                    if (i == 3) {
                        type = "supermarket";
                    }
                    if (i == 4) {
                        type = "bank";
                    }
                    if (i == 5) {
                        type = "post_office";
                    }
                    HttpHeaders headers1 = new HttpHeaders();
                    headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                    HttpEntity<String> entity1 = new HttpEntity<String>(headers1);
                    String url1 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&type={type}&key={key}&language={language}";
                    Map<String, String> uriVariables1 = new HashMap<>();
                    uriVariables1.put("location", locationRealEstate);
                    uriVariables1.put("radius", "3000");
                    uriVariables1.put("type", type);
                    uriVariables1.put("key", "AIzaSyDPzD4tPUGV3HGIiv7fVcWEFEQ0r1AAxwg");
                    uriVariables1.put("language", "vi");
                    WrapperNearBySearchDto response1 =  restTemplate.getForObject(url1, WrapperNearBySearchDto.class, uriVariables1);

                    for (NearBySearchDto j:response1.getResults()) {
                        Facility facility = new Facility();
                        Integer facilityId = 0;
                        facility.setFacilityTypeId(em.find(FacilityType.class, i));
                        facility.setFacilityName(j.getName());
                        facility.setLatitude(j.getGeometry().getLocation().getLat());
                        facility.setLongitude(j.getGeometry().getLocation().getLng());
                        facility.setAddress(j.getVicinity());
                        facilityId = (Integer) session.save(facility);

                        RealEstateFacility realEstateFacility = new RealEstateFacility();
                        realEstateFacility.setRealEstateDetail(em.find(RealEstateDetail.class, realEstateDetailId));
                        realEstateFacility.setFacility(em.find(Facility.class, facilityId));

//                      HaversineDistance
                        final int R = 6371;
                        Double lat1 = realEstateDetail.getLatitude();
                        Double lon1 = realEstateDetail.getLongitude();
                        Double lat2 = facility.getLatitude();
                        Double lon2 = facility.getLongitude();
                        Double latDistance = (lat2-lat1) * Math.PI / 180;
                        Double lonDistance = (lon2-lon1) * Math.PI / 180;
                        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                        Double distance = R * c;

                        realEstateFacility.setDistance(distance);

                        session.save(facility);
                        session.save(realEstateFacility);
                    }
                }
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstate(UpdateRealEstateDto updateRealEstateDto) {
            Session session = em.unwrap(Session.class);
            Street street = new Street();
            Integer streetId = 0;
            StreetWard streetWard = new StreetWard();
            Integer streetWardId = 0;
            try{
                java.sql.Timestamp  sqlDate = new java.sql.Timestamp (new java.util.Date().getTime());
                em.createNativeQuery(Query.updateRealEstate)
                        .setParameter("id", updateRealEstateDto.getId())
                        .setParameter("title", updateRealEstateDto.getTitle())
                        .setParameter("sellerId", updateRealEstateDto.getSellerId())
                        .setParameter("dataentryId", updateRealEstateDto.getDataentryId())
                        .setParameter("createAt", sqlDate)
                        .executeUpdate();

                street.setName(updateRealEstateDto.getStreetName());
                streetId = (Integer) session.save(street);

                streetWard.setStreet(em.find(Street.class, streetId));
                streetWard.setWard(em.find(Ward.class, updateRealEstateDto.getWardId()));

                streetWardId = (Integer) session.save(streetWard);

                String address = updateRealEstateDto.getAddress();

//                int addressDb = em.createNativeQuery(Query.getAddressDb)
//                        .executeUpdate();
//
//                if (address.equals(addressDb)){
//
//                }

                em.createNativeQuery(Query.deleteImage)
                        .setParameter("realEstateDetailId", updateRealEstateDto.getId())
                        .executeUpdate();

                for (ImageResource i: updateRealEstateDto.getImages()) {
                    ImageResource imageResource = new ImageResource();
                    imageResource.setRealEstateDetail(em.find(RealEstateDetail.class, updateRealEstateDto.getId()));
                    imageResource.setImgUrl(i.getImgUrl());
                    session.save(imageResource);
                }

                em.createNativeQuery(Query.deleteFacility)
                        .setParameter("realEstateDetailId", updateRealEstateDto.getId())
                        .executeUpdate();

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<String>(headers);
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}";
                Map<String, String> uriVariables = new HashMap<>();
                uriVariables.put("address", address);
                uriVariables.put("key", "AIzaSyAk_HxKWrfBT1g9WkfL0gqRIa9HD0d7Q0I");
                WrapperGeometryDto response =  restTemplate.getForObject(url, WrapperGeometryDto.class, uriVariables);

                Double realEstateLat = response.getResults().get(0).getGeometry().getLocation().getLat();
                Double realEstateLng = response.getResults().get(0).getGeometry().getLocation().getLng();

                String locationRealEstate = Double.toString(realEstateLat).concat(", ").concat(Double.toString(realEstateLng));

                for (int i = 1; i <= 5; i++){
                    String type = null;
                    if (i == 1) {
                        type = "school";
                    }
                    if (i == 2) {
                        type = "hospital";
                    }
                    if (i == 3) {
                        type = "supermarket";
                    }
                    if (i == 4) {
                        type = "bank";
                    }
                    if (i == 5) {
                        type = "post_office";
                    }
                    HttpHeaders headers1 = new HttpHeaders();
                    headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                    HttpEntity<String> entity1 = new HttpEntity<String>(headers1);
                    String url1 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&type={type}&key={key}&language={language}";
                    Map<String, String> uriVariables1 = new HashMap<>();
                    uriVariables1.put("location", locationRealEstate);
                    uriVariables1.put("radius", "3000");
                    uriVariables1.put("type", type);
                    uriVariables1.put("key", "AIzaSyDPzD4tPUGV3HGIiv7fVcWEFEQ0r1AAxwg");
                    uriVariables1.put("language", "vi");
                    WrapperNearBySearchDto response1 =  restTemplate.getForObject(url1, WrapperNearBySearchDto.class, uriVariables1);

                    for (NearBySearchDto j:response1.getResults()) {
                        Facility facility = new Facility();
                        Integer facilityId = 0;
                        facility.setFacilityTypeId(em.find(FacilityType.class, i));
                        facility.setFacilityName(j.getName());
                        facility.setLatitude(j.getGeometry().getLocation().getLat());
                        facility.setLongitude(j.getGeometry().getLocation().getLng());
                        facility.setAddress(j.getVicinity());
                        facilityId = (Integer) session.save(facility);

                        RealEstateFacility realEstateFacility = new RealEstateFacility();
                        realEstateFacility.setRealEstateDetail(em.find(RealEstateDetail.class, updateRealEstateDto.getId()));
                        realEstateFacility.setFacility(em.find(Facility.class, facilityId));

//                      HaversineDistance
                        final int R = 6371;
                        Double lat1 = realEstateLat;
                        Double lon1 = realEstateLng;
                        Double lat2 = facility.getLatitude();
                        Double lon2 = facility.getLongitude();
                        Double latDistance = (lat2-lat1) * Math.PI / 180;
                        Double lonDistance = (lon2-lon1) * Math.PI / 180;
                        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                        Double distance = R * c;

                        realEstateFacility.setDistance(distance);

                        session.save(facility);
                        session.save(realEstateFacility);
                    }
                }

                em.createNativeQuery(Query.updateRealEstateDetail)
                        .setParameter("id", updateRealEstateDto.getId())
                        .setParameter("streetWardId", streetWardId)
                        .setParameter("realEstateNo", updateRealEstateDto.getRealEstateNo())
                        .setParameter("lat", realEstateLat)
                        .setParameter("lng", realEstateLng)
                        .setParameter("typeId", updateRealEstateDto.getTypeId())
                        .setParameter("description", updateRealEstateDto.getDescription())
                        .setParameter("length", updateRealEstateDto.getLength())
                        .setParameter("width", updateRealEstateDto.getWidth())
                        .setParameter("area", updateRealEstateDto.getArea())
                        .setParameter("floor", updateRealEstateDto.getFloor())
                        .setParameter("price", updateRealEstateDto.getPrice())
                        .setParameter("direction", updateRealEstateDto.getDirection())
                        .setParameter("balconyDirection", updateRealEstateDto.getBalconyDirection())
                        .setParameter("project", updateRealEstateDto.getProject())
                        .setParameter("investor", updateRealEstateDto.getInvestor())
                        .setParameter("juridical", updateRealEstateDto.getJuridical())
                        .setParameter("furniture", updateRealEstateDto.getFurniture())
                        .setParameter("numberOfBedroom", updateRealEstateDto.getNumberOfBedroom())
                        .setParameter("numberOfBathroom", updateRealEstateDto.getNumberOfBathroom())
                        .executeUpdate();

            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssignDto updateRealEstateByManagerAssignDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateByManagerAssign)
                        .setParameter("id", updateRealEstateByManagerAssignDto.getId())
                        .setParameter("staffId", updateRealEstateByManagerAssignDto.getStaffId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateStatus(UpdateStatusDto updateStatusDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateStatus)
                        .setParameter("id", updateStatusDto.getId())
                        .setParameter("status", updateStatusDto.getStatus())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateDetailLatLng(UpdateLatLngDto updateLatLngDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateDetailLatLng)
                        .setParameter("id", updateLatLngDto.getId())
                        .setParameter("lat", updateLatLngDto.getLat())
                        .setParameter("lng", updateLatLngDto.getLng())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateBuyerId(UpdateBuyerIdDto updateBuyerIdDto) {
            try{
                em.createNativeQuery(Query.updateBuyerId)
                        .setParameter("id", updateBuyerIdDto.getId())
                        .setParameter("buyerId", updateBuyerIdDto.getBuyerId())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateRejected(UpdateRejectedDto updateRejectedDto) {
            try{
                em.createNativeQuery(Query.updateRealEstateRejected)
                        .setParameter("id", updateRejectedDto.getId())
                        .setParameter("reason", updateRejectedDto.getReason())
                        .executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    class Query{
        public static String getRealEstate = "select r.id as id, \n" +
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
                "rd.direction as direction,\n" +
                "rd.number_of_bedroom as numberOfBedroom,\n" +
                "rd.number_of_bathroom as numberOfBathroom,\n" +
                "rd.project as project,\n" +
                "i.id as imgId,\n" +
                "i.img_url as imageUrl,\n" +
                "r.create_at as createAt,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.id as wardId,\n" +
                "w.name as wardName,\n" +
                "d.id as disId,\n" +
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
                "and ((:direction is null) or (direction = :direction))\n" +
                "and ((:numberOfBedroom is null) or (numberOfBedroom >= :numberOfBedroom))\n" +
                "and ((:numberOfBathroom is null) or (numberOfBathroom >= :numberOfBathroom))\n" +
                "and ((:search is null) or (search like concat('%', concat(:search, '%'))))\n" +
                "and ((:disId is null) or (disId = :disId))\n" +
                "and ((:wardId is null) or (wardId = :wardId))\n" ;
//                "order by r.view DESC";

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
                "rt.id as typeId,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.username as sellerUserName, \n" +
                "s.fullname as sellerName, \n" +
                "s.avatar as sellerAvatar,\n" +
                "st.id as staffId,\n" +
                "st.username as staffUserName,\n" +
                "st.fullname as staffName,\n" +
                "st.avatar as staffAvatar,\n" +
                "rd.direction as direction,\n" +
                "rd.balcony_direction as balconyDirection,\n" +
                "rd.length as length,\n" +
                "rd.width as width,\n" +
                "rd.area as area,\n" +
                "rd.floor as floor,\n" +
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
                "f.latitude as latitudeFacility,\n" +
                "f.longitude as longitudeFacility,\n" +
                "f.address as addressFacility,\n" +
                "rf.distance as distance,\n" +
                "rd.real_estate_no as realEstateNo,\n" +
                "street.name as streetName,\n" +
                "w.id as wardId,\n" +
                "w.name as wardName,\n" +
                "d.id as disId, \n" +
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
                "de.id as dataentryId,\n" +
                "de.fullname as dataentryName,\n" +
                "de.avatar as dataentryAvatar,\n" +
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
                "left join user de on r.dataentry_id = de.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where st.id is null \n" +
                "and de.id is not null \n" +
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
                "de.id as dataentryId,\n" +
                "de.fullname as dataentryName,\n" +
                "de.avatar as dataentryAvatar,\n" +
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
                "left join user de on r.dataentry_id = de.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where st.id is not null \n" +
                "and de.id is not null \n" +
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

        public static String getNumberOfRealEstateByStaff = "select count(r.id) as numberOfRealEstate\n" +
                "from real_estate r\n" +
                "where staff_id = :staffId \n" +
                "and status = :status";

        public static String getAllRealEstateType = "select rt.id as id, rt.name as name\n" +
                "from real_estate_type rt";

        public static String getAllStaff = "select u.id as id, \n" +
                "u.fullname as fullname, \n" +
                "u.avatar as avatar, \n" +
                "u.phone as phone, \n" +
                "d.name as disName \n" +
                "from user u \n" +
                "left join role role on role.id = u.role_id \n" +
                "left join working_area w on w.staff_id = u.id \n" +
                "left join district d on d.id = w.district_id \n" +
                "where role.id = 3 \n" +
                "and ((:phone is null) or (phone = :phone))\n" +
                "and ((:fullname is null) or (fullname like concat(:fullname, '%')))";

        public static String getRealEstateByDataentry = "select r.id as id, \n" +
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
                "de.id as dataentryId,\n" +
                "de.fullname as dataentryName,\n" +
                "de.avatar as dataentryAvatar,\n" +
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
                "left join user de on r.dataentry_id = de.id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where de.id = :dataentryId \n" +
                "and r.status = :status \n" +
                "order by r.create_at DESC";

        public static String getRealEstatesNotVerifyByDataentry = "select r.id as id, \n" +
                "r.title as title, \n" +
                "rd.description as description,\n" +
                "rt.name as typeName,\n" +
                "r.view as view, \n" +
                "s.id as sellerId, \n" +
                "s.fullname as sellerName, \n" +
                "s.avatar as sellerAvatar, \n" +
                "de.id as dataentryId,\n" +
                "de.fullname as dataentryName,\n" +
                "de.avatar as dataentryAvatar,\n" +
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
                "left join user de on r.dataentry_id = de.id\n" +
                "left join real_estate_type rt on rt.id = rd.type_id\n" +
                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
                "left join street street on sw.street_id = street.id\n" +
                "left join ward w on sw.ward_id = w.id\n" +
                "left join district d on w.district_id = d.id\n" +
                "where de.id is null \n" +
                "order by r.create_at DESC";

        public static String updateRealEstateStatus = "update real_estate set status = :status where id = :id";

        public static String updateBuyerId = "update real_estate set buyer_id = :buyerId where id = :id";

        public static String updateRealEstateRejected = "update real_estate set status = 'rejected', reason = :reason where id = :id";

        public static String updateRealEstateDetailLatLng = "update real_estate_detail set latitude = :lat, longitude = :lng where id = :id";

        public static String updateRealEstateDetail = "update real_estate_detail set street_ward_id = :streetWardId, \n" +
                "real_estate_no = :realEstateNo, \n" +
                "latitude = :lat, \n" +
                "longitude = :lng, \n" +
                "type_id = :typeId, \n" +
                "description = :description, \n" +
                "length = :length, \n" +
                "width = :width, \n" +
                "area = :area, \n" +
                "floor = :floor, \n" +
                "price = :price, \n" +
                "direction = :direction, \n" +
                "balcony_direction = :balconyDirection, \n" +
                "project = :project, \n" +
                "investor = :investor, \n" +
                "juridical = :juridical, \n" +
                "furniture = :furniture, \n" +
                "number_of_bedroom = :numberOfBedroom, \n" +
                "number_of_bathroom = :numberOfBathroom \n" +
                "where id = :id";

//        public static String getAddressDb = "select concat(street.name, ', ', w.name, ', ', d.name) as address\n" +
//                "from real_estate r\n" +
//                "left join real_estate_detail rd on r.id = rd.id\n" +
//                "left join street_ward sw on rd.street_ward_id = sw.id\n" +
//                "left join street street on sw.street_id = street.id\n" +
//                "left join ward w on sw.ward_id = w.id\n" +
//                "left join district d on w.district_id = d.id\n" +
//                "where r.status = 'inactive'";

        public static String updateRealEstate = "update real_estate set seller_id = :sellerId, \n" +
                "dataentry_id = :dataentryId, \n" +
                "title = :title, \n" +
                "create_at = :createAt \n" +
                "where id = :id";

        public static String deleteFacility = "delete from real_estate_facility \n" +
                "where real_estate_detail_id = :realEstateDetailId";

        public static String deleteImage = "delete from image_resource \n" +
                "where real_estate_detail_id = :realEstateDetailId";

        public static String updateRealEstateByManagerAssign = "update real_estate set staff_id = :staffId where id = :id";

        public static String updateView = "update real_estate set view = view + 1 where id = :id";
    }
}