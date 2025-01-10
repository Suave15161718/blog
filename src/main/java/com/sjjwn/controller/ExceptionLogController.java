package com.sjjwn.controller;

import com.sjjwn.annotation.OptLog;
import com.sjjwn.model.dto.ExceptionLogDTO;
import com.sjjwn.model.vo.ResultVO;
import com.sjjwn.service.ExceptionLogService;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.dto.PageResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sjjwn.constant.OptTypeConstant.DELETE;

@Api(tags = "异常日志模块")
@RestController
@RequestMapping("/api")
public class ExceptionLogController {

    @Resource
    private ExceptionLogService exceptionLogService;

    @ApiOperation("获取异常日志")
    @GetMapping("/admin/exception/logs")
    public ResultVO<PageResultDTO<ExceptionLogDTO>> listExceptionLogs(ConditionVO conditionVO) {
        return ResultVO.ok(exceptionLogService.listExceptionLogs(conditionVO));
    }

    @OptLog(optType = DELETE)
    @ApiOperation(value = "删除异常日志")
    @DeleteMapping("/admin/exception/logs")
    public ResultVO<?> deleteExceptionLogs(@RequestBody List<Integer> exceptionLogIds) {
        exceptionLogService.removeByIds(exceptionLogIds);
        return ResultVO.ok();
    }

}
