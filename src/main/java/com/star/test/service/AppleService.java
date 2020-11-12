package com.star.test.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.star.test.domain.Apple;
import com.star.test.domain.QApple;
import com.star.test.repository.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class AppleService {

    @Autowired
    private AppleRepository appleRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * JPA QueryDsl Predicate
     *
     * @return
     */
    public List<Apple> queryAppleListByJpaPredicate() {
        QApple qApple = QApple.apple;
        Predicate predicate = qApple.id.longValue().lt(3L).and(qApple.name.like("star"));

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        return appleRepository.findAll(predicate, pageRequest).stream().collect(toList());
    }

    /**
     * QueryDsl Multiple Field Reflect Query
     * @param apple
     * @return
     */
    public List<Apple> queryAppleListByQueryDsl(Apple apple) {
        QApple qApple = QApple.apple;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Field[] declaredFields = apple.getClass().getDeclaredFields();
        List<Field> fields = Arrays.stream(declaredFields).collect(toList());

        fields.stream().forEach(value -> {
            try {
                value.setAccessible(true);
                if (Objects.isNull(value.get(apple))) {
                    return;
                }

                Field field = qApple.getClass().getDeclaredField(value.getName());
                field.setAccessible(true);
                Object qObject = field.get(qApple);

                SimpleExpression expression = (SimpleExpression) qObject;
                booleanBuilder.and(expression.eq(value.get(apple)));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        List<Apple> fetch = jpaQueryFactory.selectFrom(qApple).where(booleanBuilder).fetch();
        return fetch;
    }


}
