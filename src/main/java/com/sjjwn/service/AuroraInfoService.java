package com.sjjwn.service;

import com.sjjwn.model.dto.AboutDTO;
import com.sjjwn.model.dto.AuroraAdminInfoDTO;
import com.sjjwn.model.dto.AuroraHomeInfoDTO;
import com.sjjwn.model.dto.WebsiteConfigDTO;
import com.sjjwn.model.vo.AboutVO;
import com.sjjwn.model.vo.WebsiteConfigVO;

public interface AuroraInfoService {

    void report();

    AuroraHomeInfoDTO getAuroraHomeInfo();

    AuroraAdminInfoDTO getAuroraAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}
