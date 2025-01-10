package com.sjjwn.mapper;

import com.sjjwn.entity.About;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AboutMapper {

    Integer updateById(@Param("bean") About about);

    About selectById(@Param("id") int defaultAboutId);
}
