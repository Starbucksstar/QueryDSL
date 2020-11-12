package com.star.test.repository;

import com.star.test.domain.Apple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleRepository extends JpaRepository<Apple, Long>, QuerydslPredicateExecutor<Apple> {

}
