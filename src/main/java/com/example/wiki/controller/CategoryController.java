package com.example.wiki.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.wiki.entity.Category;
import com.example.wiki.entity.Ebook;
import com.example.wiki.mapper.EbookMapper;
import com.example.wiki.respose.CommonResponse;
import com.example.wiki.respose.CategoryResp;
import com.example.wiki.respose.EbookResp;
import com.example.wiki.service.EbookService;
import com.example.wiki.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电子书(Category)表控制层
 *
 * @author makejava
 * @since 2023-02-12 15:53:09
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private CategoryServiceImpl categoryService;


    @GetMapping("/list")
    public CommonResponse<Category> getList(Category category) {
        return new CommonResponse(true, "null", categoryService.getBookList(category));
    }



    @GetMapping("/list/{name}")
    public CommonResponse<CategoryResp> getLikeBookList(CategoryResp category, @PathVariable String name) {
        return new CommonResponse(true, null, categoryService.getLikeList(category, name));
    }

    /**
     * @param category
     * @param current
     * @param pagesize
     * @return
     */
//    分页查询
    @GetMapping("/list/{current}/{pagesize}")
    public CommonResponse<IPage> getPageBookList(CategoryResp category, @PathVariable() Integer current, @PathVariable() Integer pagesize) {
        return new CommonResponse<IPage>(true, null, categoryService.getPageBook(category, current, pagesize));
    }

    //    进行数据回显
    @GetMapping("/detail/{id}")
    public CommonResponse showDetail(CategoryResp category, @PathVariable int id) {
        if (categoryService.showBookDetail(category, id) != null) {
            return new CommonResponse<>(true, null, categoryService.showBookDetail(category, id));
        } else {
            return new CommonResponse(false, null, "操作失败");
        }
    }

    //    进行数据的修改
    @PostMapping("/updateBook")
    public CommonResponse<Boolean> editBook(@RequestBody CategoryResp category) {
        return new CommonResponse<Boolean>(true, "修改成功", categoryService.editBook(category));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Boolean> deleteBook(@PathVariable Long id){
        return new CommonResponse<>(true,null,categoryService.deleteBook(id));
    }

//    @GetMapping("/getParentId/{id}")
//    public Category getParentId(Category category,@PathVariable Long id){
//        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
//        queryWrapper.eq(Category::getId,id);
//        return categoryService.getOne(queryWrapper);
//    }

    @GetMapping("/getParentId/{id}")
    public List<Ebook> getParentEbook(@PathVariable Long id){
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Ebook::getCategory2Id,id);
        return ebookMapper.selectList(queryWrapper);
    }


}

