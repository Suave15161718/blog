package com.sjjwn.mapper;

import com.sjjwn.model.dto.ArticleSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



@Mapper
public interface ElasticsearchMapper extends ElasticsearchRepository<ArticleSearchDTO,Integer> {

}
