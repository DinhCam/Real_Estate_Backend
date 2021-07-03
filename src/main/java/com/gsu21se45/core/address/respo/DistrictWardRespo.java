package com.gsu21se45.core.address.respo;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.address.dto.WardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DistrictWardRespo {

    @Autowired
    private EntityManager em;

    public boolean insertDistrict(DistrictDto districtDto){
        try{
            em.createNativeQuery(Query.insertDistrict)
                    .setParameter("disId", districtDto.getId())
                    .setParameter("disName", districtDto.getName())
                    .executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertWard(WardDto wardDto, DistrictDto districtDto){
        try{
            em.createNativeQuery(Query.insertWard)
                    .setParameter("wardId", wardDto.getId())
                    .setParameter("disId", districtDto.getId())
                    .setParameter("wardName", wardDto.getName())
                    .executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static class Query{
        public static String insertDistrict = "INSERT INTO district (`id`, `name`) VALUES (:disId, :disName)";
        public static String insertWard = "INSERT INTO ward (`id`, `district_id`, `name`) VALUES (:wardId, :disId, :wardName)";
    }

}
