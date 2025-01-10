package com.sjjwn.service;

import com.sjjwn.model.dto.CommentAdminDTO;
import com.sjjwn.model.dto.CommentDTO;
import com.sjjwn.model.dto.ReplyDTO;
import com.sjjwn.entity.Comment;
import com.sjjwn.model.vo.CommentVO;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.dto.PageResultDTO;
import com.sjjwn.model.vo.ReviewVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void saveComment(CommentVO commentVO);

    PageResultDTO<CommentDTO> listComments(CommentVO commentVO);

    List<ReplyDTO> listRepliesByCommentId(Integer commentId);

    List<CommentDTO> listTopSixComments();

    PageResultDTO<CommentAdminDTO> listCommentsAdmin(ConditionVO conditionVO);

    void updateCommentsReview(ReviewVO reviewVO);

}
