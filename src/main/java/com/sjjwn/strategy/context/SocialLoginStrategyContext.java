package com.sjjwn.strategy.context;


import com.sjjwn.model.dto.UserInfoDTO;
import com.sjjwn.enums.LoginTypeEnum;
import com.sjjwn.strategy.SocialLoginStrategy;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginStrategyContext {

    @Resource
    private Map<String, SocialLoginStrategy> socialLoginStrategyMap;

    public UserInfoDTO executeLoginStrategy(String data, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
    }

}
