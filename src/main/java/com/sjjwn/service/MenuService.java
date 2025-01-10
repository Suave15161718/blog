package com.sjjwn.service;

import com.sjjwn.model.dto.LabelOptionDTO;
import com.sjjwn.model.dto.MenuDTO;
import com.sjjwn.model.dto.UserMenuDTO;
import com.sjjwn.entity.Menu;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.vo.IsHiddenVO;
import com.sjjwn.model.vo.MenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<MenuDTO> listMenus(ConditionVO conditionVO);

    void saveOrUpdateMenu(MenuVO menuVO);

    void updateMenuIsHidden(IsHiddenVO isHiddenVO);

    void deleteMenu(Integer menuId);

    List<LabelOptionDTO> listMenuOptions();

    List<UserMenuDTO> listUserMenus();

}
