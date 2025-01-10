package com.sjjwn.service;

import com.sjjwn.model.dto.ExceptionLogDTO;
import com.sjjwn.entity.ExceptionLog;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ExceptionLogService extends IService<ExceptionLog> {

    PageResultDTO<ExceptionLogDTO> listExceptionLogs(ConditionVO conditionVO);

}
