package com.example.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki.Util.CopyUtil;
import com.example.wiki.Util.SnowId;
import com.example.wiki.entity.Content;
import com.example.wiki.entity.Doc;
import com.example.wiki.mapper.ContentMapper;
import com.example.wiki.mapper.DocMapper;
import com.example.wiki.respose.DocResp;
import com.example.wiki.service.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子书(Doc)表服务实现类
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
@Service
@Slf4j
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

    @Resource
    private DocService docService;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowId snowId;

    @Override
    public List<Doc> getDocList(Doc doc) {
        LambdaQueryWrapper<Doc> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Doc::getSort);
        return docService.list(lambdaQueryWrapper);
    }

    public List<DocResp> getDocListDoc(Doc doc,Long id) {
        LambdaQueryWrapper<Doc> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Doc::getEbookId,id);
        List<Doc> list = docService.list(lambdaQueryWrapper);
        List<DocResp> docRespList = CopyUtil.copyList(list, DocResp.class);
        return docRespList;
    }


    @Override
    public List<DocResp> getLikeList(DocResp doc, String name) {
        LambdaQueryWrapper<Doc> docList = new LambdaQueryWrapper<>();
        docList.like(Doc::getName, name);
        List<Doc> docs = docService.getDocList(doc);
//      将进行对象的拷贝
        ArrayList<DocResp> docResps = new ArrayList<>();
        for (Doc doc1 : docs) {
            DocResp docResp = new DocResp();
            BeanUtils.copyProperties(doc1, docResp);
            docResps.add(docResp);
        }
        return docResps;
    }

    @Override
    public IPage<Doc> getPageDoc(Doc doc, int current, int pagesize) {
        IPage<Doc> page = new Page<>(current, pagesize);
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        return docService.page(page);
    }

    @Override
    public DocResp showDocDetail(Doc doc, Long id) {
        Doc doc1 = docService.getById(id);
        Content content = contentMapper.selectById(id);
        DocResp docResp = CopyUtil.copy(doc1, DocResp.class);
        if (content != null) {
            docResp.setContent(content.getContent());
        }
        return docResp;
    }

    @Override
    public void editDoc(Doc doc) {
        Doc doc1 = CopyUtil.copy(doc, Doc.class);
        Content content = CopyUtil.copy(doc, Content.class);
        if (ObjectUtils.isEmpty(doc.getId())) {
            log.info("进行数据的添加");
            long id = snowId.nextId();
            doc.setId(id);
            content.setId(id);
            contentMapper.insertContent(content);
            docService.save(doc1);
        } else {
            docService.updateById(doc);
            int contentUpdata = contentMapper.updateById(content);
            if (contentUpdata == 0) {
                contentMapper.insertContent(content);
            }

        }

    }

    @Override
    public Boolean deleteDoc(@PathVariable Long id) {
        return docService.removeById(id);
    }

    public String findContent(Long id){
        Content content = contentMapper.selectById(id);
        return content.getContent();
    }

}
