package com.star.test.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QApple is a Querydsl query type for Apple
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApple extends EntityPathBase<Apple> {

    private static final long serialVersionUID = 13399005L;

    public static final QApple apple = new QApple("apple");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QApple(String variable) {
        super(Apple.class, forVariable(variable));
    }

    public QApple(Path<? extends Apple> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApple(PathMetadata metadata) {
        super(Apple.class, metadata);
    }

}

