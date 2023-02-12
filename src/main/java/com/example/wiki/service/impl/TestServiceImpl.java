package com.example.wiki.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki.entity.Test;
import com.example.wiki.mapper.TestMapper;
import com.example.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService {

    @Resource
    private TestMapper testMapper;


    public void selectAll() {
         testMapper.deleteById(1);

    }
}
