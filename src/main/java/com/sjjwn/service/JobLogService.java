package com.sjjwn.service;


import com.sjjwn.model.dto.JobLogDTO;
import com.sjjwn.entity.JobLog;
import com.sjjwn.model.vo.JobLogSearchVO;
import com.sjjwn.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface JobLogService extends IService<JobLog> {

    PageResultDTO<JobLogDTO> listJobLogs(JobLogSearchVO jobLogSearchVO);

    void deleteJobLogs(List<Integer> ids);

    void cleanJobLogs();

    List<String> listJobLogGroups();

}
