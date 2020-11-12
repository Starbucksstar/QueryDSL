package com.star.test;

import com.star.test.domain.Apple;
import com.star.test.service.AppleService;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private AppleService appleService;

    @Test
    void testJpaPredicateQuery() {
        List<Apple> apples = appleService.queryAppleListByJpaPredicate();
        System.out.println(JSONArray.toJSONString(apples));
    }


    @Test
    void testQueryDslQuery() {
        Apple star = Apple.builder().name("star").id(1L).build();
        System.out.println(JSONArray.toJSONString(appleService.queryAppleListByQueryDsl(star)));
    }
}
