package com.sjjwn.service.impl;

import com.alibaba.fastjson.JSON;
import com.sjjwn.entity.Talk;
import com.sjjwn.enums.CommentTypeEnum;
import com.sjjwn.exception.BizException;
import com.sjjwn.mapper.CommentMapper;
import com.sjjwn.mapper.TalkMapper;
import com.sjjwn.model.dto.CommentCountDTO;
import com.sjjwn.model.dto.PageResultDTO;
import com.sjjwn.model.dto.TalkAdminDTO;
import com.sjjwn.model.dto.TalkDTO;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.vo.TalkVO;
import com.sjjwn.service.TalkService;
import com.sjjwn.util.BeanCopyUtil;
import com.sjjwn.util.CommonUtil;
import com.sjjwn.util.PageUtil;
import com.sjjwn.util.UserUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sjjwn.enums.TalkStatusEnum.PUBLIC;


@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {

    @Resource
    private TalkMapper talkMapper;

    @Resource
    private CommentMapper commentMapper;

    @Override
    public PageResultDTO<TalkDTO> listTalks() {
        Integer count = talkMapper.selectCount((new LambdaQueryWrapper<Talk>()
                .eq(Talk::getStatus, PUBLIC.getStatus())));
        if (count == 0) {
            return new PageResultDTO<>();
        }
        List<TalkDTO> talkDTOs = talkMapper.listTalks(PageUtil.getLimitCurrent(), PageUtil.getSize());
        List<Integer> talkIds = talkDTOs.stream()
                .map(TalkDTO::getId)
                .collect(Collectors.toList());
        Map<Integer, Integer> commentCountMap = commentMapper.listCommentCountByTypeAndTopicIds(CommentTypeEnum.TALK.getType(), talkIds)
                .stream()
                .collect(Collectors.toMap(CommentCountDTO::getId, CommentCountDTO::getCommentCount));
        talkDTOs.forEach(item -> {
            item.setCommentCount(commentCountMap.get(item.getId()));
            if (Objects.nonNull(item.getImages())) {
                item.setImgs(CommonUtil.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResultDTO<>(talkDTOs, count);
    }

    @Override
    public TalkDTO getTalkById(Integer talkId) {
        TalkDTO talkDTO = talkMapper.getTalkById(talkId);
        if (Objects.isNull(talkDTO)) {
            throw new BizException("说说不存在");
        }
        if (Objects.nonNull(talkDTO.getImages())) {
            talkDTO.setImgs(CommonUtil.castList(JSON.parseObject(talkDTO.getImages(), List.class), String.class));
        }
        CommentCountDTO commentCountDTO = commentMapper.listCommentCountByTypeAndTopicId(CommentTypeEnum.TALK.getType(), talkId);
        if (Objects.nonNull(commentCountDTO)) {
            talkDTO.setCommentCount(commentCountDTO.getCommentCount());
        }
        return talkDTO;
    }

    @Override
    public void saveOrUpdateTalk(TalkVO talkVO) {
        Talk talk = BeanCopyUtil.copyObject(talkVO, Talk.class);
        talk.setUserId(UserUtil.getUserDetailsDTO().getUserInfoId());
        this.saveOrUpdate(talk);
    }

    @Override
    public void deleteTalks(List<Integer> talkIds) {
        talkMapper.deleteBatchIds(talkIds);
    }

    @Override
    public PageResultDTO<TalkAdminDTO> listBackTalks(ConditionVO conditionVO) {
        Integer count = talkMapper.selectCount(new LambdaQueryWrapper<Talk>()
                .eq(Objects.nonNull(conditionVO.getStatus()), Talk::getStatus, conditionVO.getStatus()));
        if (count == 0) {
            return new PageResultDTO<>();
        }
        List<TalkAdminDTO> talkDTOs = talkMapper.listTalksAdmin(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        talkDTOs.forEach(item -> {
            if (Objects.nonNull(item.getImages())) {
                item.setImgs(CommonUtil.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResultDTO<>(talkDTOs, count);
    }

    @Override
    public TalkAdminDTO getBackTalkById(Integer talkId) {
        TalkAdminDTO talkBackDTO = talkMapper.getTalkByIdAdmin(talkId);
        if (Objects.nonNull(talkBackDTO.getImages())) {
            talkBackDTO.setImgs(CommonUtil.castList(JSON.parseObject(talkBackDTO.getImages(), List.class), String.class));
        }
        return talkBackDTO;
    }

}

