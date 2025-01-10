package com.sjjwn.listener;

import com.sjjwn.entity.ExceptionLog;
import com.sjjwn.entity.OperationLog;
import com.sjjwn.event.ExceptionLogEvent;
import com.sjjwn.event.OperationLogEvent;
import com.sjjwn.mapper.ExceptionLogMapper;
import com.sjjwn.mapper.OperationLogMapper;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuroraListener {

    @Resource
    private OperationLogMapper operationLogMapper;

    @Resource
    private ExceptionLogMapper exceptionLogMapper;

    @Async
    @EventListener(OperationLogEvent.class)
    public void saveOperationLog(OperationLogEvent operationLogEvent) {
        operationLogMapper.insert((OperationLog) operationLogEvent.getSource());
    }

    @Async
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent exceptionLogEvent) {
        exceptionLogMapper.insert((ExceptionLog) exceptionLogEvent.getSource());
    }

}
