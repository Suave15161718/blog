package com.sjjwn.service;

import com.sjjwn.model.dto.FriendLinkAdminDTO;
import com.sjjwn.model.dto.FriendLinkDTO;
import com.sjjwn.entity.FriendLink;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.vo.FriendLinkVO;
import com.sjjwn.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLinkDTO> listFriendLinks();

    PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO);

    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
