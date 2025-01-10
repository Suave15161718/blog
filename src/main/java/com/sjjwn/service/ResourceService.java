package com.sjjwn.service;

import com.sjjwn.model.dto.LabelOptionDTO;
import com.sjjwn.model.dto.ResourceDTO;
import com.sjjwn.entity.Resource;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    void importSwagger();

    void saveOrUpdateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);

    List<ResourceDTO> listResources(ConditionVO conditionVO);

    List<LabelOptionDTO> listResourceOption();

}
