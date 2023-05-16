package com.example.wiki.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.wiki.entity.Category;
import com.example.wiki.entity.Doc;
import com.example.wiki.respose.DocResp;
import com.example.wiki.respose.CommonResponse;
import com.example.wiki.service.impl.DocServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电子书(Doc)表控制层
 *
 * @author makejava
 * @since 2023-02-12 15:53:09
 */
@RestController
@RequestMapping("/doc")
@Slf4j
public class DocController {


    @Resource
    private DocServiceImpl docService;


    @GetMapping("/list")
    public CommonResponse<Doc> getList(Doc doc) {
        return new CommonResponse(true, "null", docService.getDocList(doc));
    }

    @GetMapping("/doclist/{id}")
    public CommonResponse<List<DocResp>> getList(Doc doc, @PathVariable Long id) {
        return new CommonResponse(true, "null", docService.getDocListDoc(doc,id));
    }

    @GetMapping("/list/{name}")
    public CommonResponse<DocResp> getLikeDocList(DocResp doc, @PathVariable String name) {
        return new CommonResponse(true, null, docService.getLikeList(doc, name));
    }

    /**
     * @param doc
     * @param current
     * @param pagesize
     * @return
     */
//    分页查询
    @GetMapping("/list/{current}/{pagesize}")
    public CommonResponse<IPage> getPageDocList(DocResp doc, @PathVariable() Integer current, @PathVariable() Integer pagesize) {
        return new CommonResponse<IPage>(true, null, docService.getPageDoc(doc, current, pagesize));
    }

    //    进行数据回显
    @GetMapping("/detail/{id}")
    public CommonResponse showDetail(DocResp doc, @PathVariable Long id) {
        if (docService.showDocDetail(doc, id) != null) {
            return new CommonResponse<>(true, null, docService.showDocDetail(doc, id));
        } else {
            return new CommonResponse(false, null, "操作失败");
        }
    }

    //    进行数据的修改
    @PostMapping("/updateDoc")
    public void editDoc(@RequestBody DocResp doc) {
        docService.editDoc(doc);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Boolean> deleteDoc(@PathVariable Long id){
        return new CommonResponse<>(true,null,docService.deleteDoc(id));
    }

    @GetMapping("/getParentId/{id}")
    public Doc getParentId(Doc doc,@PathVariable Long id){
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Doc::getId,id);
        return docService.getOne(queryWrapper);
    }

    @GetMapping("/content/{id}")
    public CommonResponse<String> findContent(@PathVariable Long id) {
        return new CommonResponse(true, "null", docService.findContent(id));
    }

}

