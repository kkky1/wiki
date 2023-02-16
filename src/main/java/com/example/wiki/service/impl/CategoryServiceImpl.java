package com.example.wiki.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki.Util.SnowId;
import com.example.wiki.entity.Category;
import com.example.wiki.mapper.CategoryMapper;
import com.example.wiki.respose.CategoryResp;
import com.example.wiki.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子书(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryService categoryService;

    @Resource
    private SnowId snowId;

    @Override
    public List<Category> getBookList(Category category) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Category::getSort);
        return categoryService.list(lambdaQueryWrapper);
    }

    @Override
    public List<CategoryResp> getLikeList(CategoryResp category, String name) {
        LambdaQueryWrapper<Category> bookList = new LambdaQueryWrapper<>();
        bookList.like(Category::getName, name);
        List<Category> categorys = categoryService.getBookList(category);
//      将进行对象的拷贝
        ArrayList<CategoryResp> categoryResps = new ArrayList<>();
        for (Category category1 : categorys) {
            CategoryResp categoryResp = new CategoryResp();
            BeanUtils.copyProperties(category1, categoryResp);
            categoryResps.add(categoryResp);
        }
        return categoryResps;
    }

    @Override
    public IPage<Category> getPageBook(Category category, int current, int pagesize) {
        IPage<Category> page = new Page<>(current, pagesize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        return categoryService.page(page);
    }

    @Override
    public Category showBookDetail(Category category,Integer id) {
        return categoryService.getById(id);
    }

    @Override
    public boolean editBook(Category category) {
        if (ObjectUtils.isEmpty(category.getId())){
            log.info("进行数据的添加");
            category.setId(snowId.nextId());
            return categoryService.save(category);
        }
        else {
            UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
            return categoryService.updateById(category);
        }

    }

    @Override
    public Boolean deleteBook(@PathVariable Long id) {
        return categoryService.removeById(id);
    }

}
