package com.sjjwn.service;

import com.sjjwn.model.dto.CategoryAdminDTO;
import com.sjjwn.model.dto.CategoryDTO;
import com.sjjwn.model.dto.CategoryOptionDTO;
import com.sjjwn.entity.Category;
import com.sjjwn.model.vo.CategoryVO;
import com.sjjwn.model.vo.ConditionVO;
import com.sjjwn.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryDTO> listCategories();

    PageResultDTO<CategoryAdminDTO> listCategoriesAdmin(ConditionVO conditionVO);

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);

    void deleteCategories(List<Integer> categoryIds);

    void saveOrUpdateCategory(CategoryVO categoryVO);

}
