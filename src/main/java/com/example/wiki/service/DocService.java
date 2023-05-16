package com.example.wiki.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wiki.entity.Doc;
import com.example.wiki.entity.Doc;
import com.example.wiki.respose.DocResp;

import java.util.List;

/**
 * 测试(Test)表服务接口
 *
 * @author makejava
 * @since 2023-02-12 15:18:49
 */
public interface DocService extends IService<Doc> {

    List<Doc> getDocList(Doc doc);

    List<DocResp> getLikeList(DocResp doc, String name);

    IPage<Doc> getPageDoc(Doc doc, int current, int pagesize);

    //    进行数据回显
    Doc showDocDetail(Doc doc,Long id);
    //    进行数据的修改
    void editDoc(Doc doc);
    //    进行删除操作
    Boolean deleteDoc(Long id);

//    int updateViewCount(Integer id);

}
