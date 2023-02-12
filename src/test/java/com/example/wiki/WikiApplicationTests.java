package com.example.wiki;

import com.example.wiki.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WikiApplicationTests {

    TestServiceImpl testServiceImpl = new TestServiceImpl();

    @Test
    void contextLoads() {
        testServiceImpl.selectAll();
    }

}
