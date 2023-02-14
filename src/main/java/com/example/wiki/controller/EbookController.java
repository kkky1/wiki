package com.example.wiki.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wiki.entity.Ebook;
import com.example.wiki.respose.CommonResponse;
import com.example.wiki.respose.EbookResp;
import com.example.wiki.service.EbookService;
import com.example.wiki.service.impl.EbookServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 电子书(Ebook)表控制层
 *
 * @author makejava
 * @since 2023-02-12 15:53:09
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {


    @Resource
    private EbookServiceImpl ebookService;

    @GetMapping("/list")
    public CommonResponse<Ebook> getList() {
        return new CommonResponse(true, "null", ebookService.getBookList());
    }

    @GetMapping("/list/{name}")
    public CommonResponse<EbookResp> getLikeBookList(EbookResp ebook, @PathVariable String name) {
        return new CommonResponse(true, null, ebookService.getLikeList(ebook, name));
    }

    /**
     * @param ebook
     * @param current
     * @param pagesize
     * @return
     */
//    分页查询
    @GetMapping("/list/{current}/{pagesize}")
    public CommonResponse<IPage> getPageBookList(EbookResp ebook, @PathVariable() Integer current, @PathVariable() Integer pagesize) {
        return new CommonResponse<IPage>(true, null, ebookService.getPageBook(ebook, current, pagesize));
    }

//    进行数据回显
    @GetMapping("/detail/{id}")
    public CommonResponse<Ebook> showDetail(EbookResp ebook,@PathVariable int id){
        if (ebookService.showBookDetail(ebook,id) != null){
            return new CommonResponse<>(true,null,ebookService.showBookDetail(ebook,id));
        }
        else{
            return new CommonResponse<Ebook>(false,"加载失败",null);
        }

    }
}

