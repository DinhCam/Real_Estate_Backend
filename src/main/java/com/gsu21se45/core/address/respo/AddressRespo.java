package com.gsu21se45.core.address.respo;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.address.dto.WardDto;
import com.gsu21se45.core.address.dto.AddressDto;
import com.gsu21se45.core.address.transformer.AddressTransformer;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface AddressRespo {
    boolean insertDistrict(DistrictDto districtDto);
    boolean insertWard(WardDto wardDto, DistrictDto districtDto);
    List<AddressDto> getAddress();

    @Repository
    class AddressRespoImpl implements AddressRespo{
        @Autowired
        private EntityManager em;

        @Override
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

        @Override
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

        @Override
        public List<AddressDto> getAddress() {
            List<AddressDto> rs = (List<AddressDto>) em
                    .createNativeQuery(Query.getAddress)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(new AddressTransformer())
                    .getResultList();
            return rs;
        }

        static class Query{
            public static String insertDistrict = "INSERT INTO district (`id`, `name`) VALUES (:disId, :disName)";

            public static String insertWard = "INSERT INTO ward (`id`, `district_id`, `name`) VALUES (:wardId, :disId, :wardName)";

            public static String getAddress = "select dis.id as id, \n" +
                    "dis.name as disName, \n" +
                    "w.id as wardId,\n" +
                    "w.name as wardName\n" +
                    "from district dis\n" +
                    "left join ward w on dis.id = w.district_id";
        }
    }
}
