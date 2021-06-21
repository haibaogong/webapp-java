package com.rigol.website.dao;

import com.rigol.website.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoDao {

    @Insert("insert into info ( id, name, phone) values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR})")
    void save(UserInfo userInfo);

    @Select("select id,name,phone from info ")
    List<UserInfo> queryAll();
}
