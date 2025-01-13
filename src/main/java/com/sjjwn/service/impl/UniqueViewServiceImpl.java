package com.sjjwn.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.sjjwn.entity.IpLocationPO;
import com.sjjwn.entity.ViewLogsPO;
import com.sjjwn.mapper.ViewMapper;
import com.sjjwn.model.dto.UniqueViewDTO;
import com.sjjwn.entity.UniqueView;
import com.sjjwn.mapper.UniqueViewMapper;
import com.sjjwn.service.UniqueViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

import com.sjjwn.util.IpUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewMapper, UniqueView> implements UniqueViewService {

    @Resource
    private UniqueViewMapper uniqueViewMapper;
    @Resource
    private ViewMapper viewMapper;

    @Override
    public List<UniqueViewDTO> listUniqueViews() {
        DateTime startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        DateTime endTime = DateUtil.endOfDay(new Date());
        return uniqueViewMapper.listUniqueViews(startTime, endTime);
    }

    @Override
    public void insertView(String ip) throws Exception {
        IpLocationPO locationByIp = IpUtil.getLocationByIp(ip);
        ViewLogsPO build = ViewLogsPO.builder()
                .ip(ip)
                .city(locationByIp.getCity())
                .province(locationByIp.getProvince())
                .address(locationByIp.getAddress())
                .msg(locationByIp.getMsg())
                .viewTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss")).build();
        viewMapper.insert(build);

    }

}
