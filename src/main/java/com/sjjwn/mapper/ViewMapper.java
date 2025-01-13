package com.sjjwn.mapper;

import com.sjjwn.entity.ViewLogsPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ViewMapper {

    @Insert("insert into login_logs (view_time, ip_address,msg,province,city,address) " +
            "VALUES (#{bean.viewTime},#{bean.ip},#{bean.msg},#{bean.province},#{bean.city},#{bean.address})")
    Integer insert(@Param("bean") ViewLogsPO build);
}
