package com.sjjwn.mapper;

import com.sjjwn.entity.WebsiteConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface WebsiteConfigMapper  {

    Integer updateById(@Param("bean") WebsiteConfig websiteConfig);

    WebsiteConfig selectById(@Param("id") int defaultConfigId);
}
