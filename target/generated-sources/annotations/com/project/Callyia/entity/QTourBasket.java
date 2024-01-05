package com.project.Callyia.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTourBasket is a Querydsl query type for TourBasket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTourBasket extends EntityPathBase<TourBasket> {

    private static final long serialVersionUID = 1772770412L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTourBasket tourBasket = new QTourBasket("tourBasket");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final QTour placeId;

    public QTourBasket(String variable) {
        this(TourBasket.class, forVariable(variable), INITS);
    }

    public QTourBasket(Path<? extends TourBasket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTourBasket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTourBasket(PathMetadata metadata, PathInits inits) {
        this(TourBasket.class, metadata, inits);
    }

    public QTourBasket(Class<? extends TourBasket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.placeId = inits.isInitialized("placeId") ? new QTour(forProperty("placeId")) : null;
    }

}

