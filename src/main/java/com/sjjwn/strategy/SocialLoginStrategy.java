package com.sjjwn.strategy;


import com.sjjwn.model.dto.UserInfoDTO;

public interface SocialLoginStrategy {

    UserInfoDTO login(String data);

}
