package com.sjjwn.service;

import com.sjjwn.model.dto.UniqueViewDTO;
import com.sjjwn.entity.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UniqueViewService extends IService<UniqueView> {

    List<UniqueViewDTO> listUniqueViews();

    void insertView(String ip) throws Exception;

}
