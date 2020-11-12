package com.star.test.controller;

import com.querydsl.core.types.Predicate;
import com.star.test.domain.Apple;
import com.star.test.repository.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class AppleController implements Serializable {

    @Autowired
    private AppleRepository appleRepository;


    /**
     * QuerydslPredicate
     * @param predicate
     * @return
     */
    @GetMapping(value = "/api/v1/hello")
    public Object hello(@QuerydslPredicate(root = Apple.class) Predicate predicate) {
        return appleRepository.findAll(predicate);
    }

}
