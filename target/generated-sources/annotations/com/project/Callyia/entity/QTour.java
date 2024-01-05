package com.project.Callyia.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTour is a Querydsl query type for Tour
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTour extends EntityPathBase<Tour> {

    private static final long serialVersionUID = 351046406L;

    public static final QTour tour = new QTour("tour");

    public final StringPath address = createString("address");

    public final StringPath checkColumn = createString("checkColumn");

    public final StringPath image = createString("image");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath placeContent = createString("placeContent");

    public final NumberPath<Long> placeId = createNumber("placeId", Long.class);

    public final StringPath placeName = createString("placeName");

    public final ListPath<TourBasket, QTourBasket> tourBaskets = this.<TourBasket, QTourBasket>createList("tourBaskets", TourBasket.class, QTourBasket.class, PathInits.DIRECT2);

    public QTour(String variable) {
        super(Tour.class, forVariable(variable));
    }

    public QTour(Path<? extends Tour> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTour(PathMetadata metadata) {
        super(Tour.class, metadata);
    }

}

