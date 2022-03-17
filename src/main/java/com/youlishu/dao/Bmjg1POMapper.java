package com.youlishu.dao;




import com.youlishu.bean.Bmjg1PO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Bmjg1POMapper {

    List<Bmjg1PO> selectCompany(String name);

    List<Bmjg1PO> selectCompanyByTwo(String name);

    boolean insertNext(@Param("name") String name, @Param("nextName") String nextName);

    boolean update(@Param("name") String name,@Param("newName") String newName);

    List<Bmjg1PO> selectCompanyByThree(String name);

    List<Bmjg1PO> selectCompanyByFour(String name);

    List<Bmjg1PO> selectCompanyByFive(String name);

    List<Bmjg1PO> selectCompanyBySix(String name);

    boolean insertNextByTwo(@Param("name") String name, @Param("nextName") String nextName);

    boolean insertNextByThree(@Param("name") String name, @Param("nextName") String nextName);

    boolean insertNextByFour(@Param("name") String name, @Param("nextName") String nextName);

    boolean insertNextByFive(@Param("name") String name, @Param("nextName") String nextName);

    boolean insertNextBySix(@Param("name") String name, @Param("nextName") String nextName);

    boolean updateByTwo(@Param("name") String name, @Param("newName") String newName);

    boolean updateByThree(@Param("name") String name, @Param("newName") String newName);

    boolean updateByFour(@Param("name") String name, @Param("newName") String newName);

    boolean updateByFive(@Param("name") String name, @Param("newName") String newName);

    boolean updateBySix(@Param("name") String name, @Param("newName") String newName);

    boolean updateByServen(@Param("name") String name, @Param("newName") String newName);

    boolean deleteByName(@Param("name") String name);

    boolean deleteByNameByTwo(@Param("name") String name);

    boolean deleteByNameByThree(@Param("name") String name);

    boolean deleteByNameByFour(@Param("name") String name);

    boolean deleteByNameByFive(@Param("name") String name);

    boolean deleteByNameBySix(@Param("name") String name);

    boolean deleteByNameByServen(@Param("name") String name);
}