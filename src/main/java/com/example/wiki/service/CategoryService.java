package com.example.wiki.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wiki.entity.Category;
import com.example.wiki.respose.CategoryResp;

import java.util.List;

/**
 * 电子书(Category)表服务接口
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
public interface CategoryService extends IService<Category> {

    List<Category> getBookList(Category category);

    List<CategoryResp> getLikeList(CategoryResp category, String name);

    IPage<Category> getPageBook(Category category, int current, int pagesize);

//    进行数据回显
    Category showBookDetail(Category category,Integer id);
//    进行数据的修改
    boolean editBook(Category category);
//    进行删除操作
    Boolean deleteBook(Long id);

}
