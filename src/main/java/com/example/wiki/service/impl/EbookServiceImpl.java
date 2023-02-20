package com.example.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki.Util.SnowId;
import com.example.wiki.entity.Ebook;
import com.example.wiki.mapper.EbookMapper;
import com.example.wiki.respose.EbookResp;
import com.example.wiki.service.EbookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子书(Ebook)表服务实现类
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
@Service
@Slf4j
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements EbookService {

    @Resource
    private EbookService ebookService;

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowId snowId;

    @Override
    public List<Ebook> getBookList() {
        return ebookMapper.getBookList();
    }

    @Override
    public List<EbookResp> getLikeList(EbookResp ebook, String name) {
        LambdaQueryWrapper<Ebook> bookList = new LambdaQueryWrapper<>();
        bookList.like(Ebook::getName, name);
        List<Ebook> ebooks = ebookMapper.selectList(bookList);
//      将进行对象的拷贝
        ArrayList<EbookResp> ebookResps = new ArrayList<>();
        for (Ebook ebook1 : ebooks) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook1, ebookResp);
            ebookResps.add(ebookResp);
        }
        return ebookResps;
    }

    @Override
    public IPage<Ebook> getPageBook(Ebook ebook, int current, int pagesize) {
        IPage<Ebook> page = new Page<>(current, pagesize);
        LambdaQueryWrapper<Ebook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Ebook::getViewCount);
        return ebookService.page(page);
    }

    @Override
    public Ebook showBookDetail(Long id) {
        return ebookService.getById(id);
    }

    @Override
    public boolean editBook(Ebook ebook) {
        if (ObjectUtils.isEmpty(ebook.getId())){
            log.info("进行数据的添加");
            ebook.setId(snowId.nextId());
            return ebookService.save(ebook);
        }
        else {
            UpdateWrapper<Ebook> updateWrapper = new UpdateWrapper<>();
            return ebookService.updateById(ebook);
        }

    }

    @Override
    public Boolean deleteBook(Long id) {
        return ebookService.removeById(id);
    }

//    进行分页查询
//    @Override
//    public IPage<Ebook> getPageBook(Ebook ebook,int current,int pagesize){
////        return ebookService.getPageBook()
//    }
}
