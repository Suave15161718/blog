package com.sjjwn.service;

import com.sjjwn.model.dto.TalkAdminDTO;
import com.sjjwn.model.dto.TalkDTO;
import com.sjjwn.entity.Talk;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.dto.PageResultDTO;
import com.sjjwn.model.vo.TalkVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface TalkService extends IService<Talk> {

    PageResultDTO<TalkDTO> listTalks();

    TalkDTO getTalkById(Integer talkId);

    void saveOrUpdateTalk(TalkVO talkVO);

    void deleteTalks(List<Integer> talkIdList);

    PageResultDTO<TalkAdminDTO> listBackTalks(ConditionVO conditionVO);

    TalkAdminDTO getBackTalkById(Integer talkId);

}
