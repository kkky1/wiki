package com.example.wiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wiki.entity.Ebook;
import com.example.wiki.respose.EbookResp;

import java.util.List;

/**
 * 电子书(Ebook)表服务接口
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
public interface EbookService extends IService<Ebook> {

    List<Ebook> getBookList();

    List<EbookResp> getLikeList(EbookResp ebook, String name);

}