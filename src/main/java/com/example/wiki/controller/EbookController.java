package com.example.wiki.controller;

import com.example.wiki.entity.Ebook;
import com.example.wiki.respose.CommonResponse;
import com.example.wiki.respose.EbookResp;
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
    public CommonResponse<Ebook> getList(){
        return new CommonResponse(true,"null",ebookService.getBookList());
    }

    @GetMapping("/list/{name}")
    public CommonResponse<EbookResp> getLikeBookList(EbookResp ebook, @PathVariable String name){
        return new CommonResponse(true,null,ebookService.getLikeList(ebook,name));
    }

}
