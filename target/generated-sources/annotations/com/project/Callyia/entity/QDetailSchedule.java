package com.project.Callyia.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDetailSchedule is a Querydsl query type for DetailSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDetailSchedule extends EntityPathBase<DetailSchedule> {

    private static final long serialVersionUID = -944124618L;

    public static final QDetailSchedule detailSchedule = new QDetailSchedule("detailSchedule");

    public final StringPath detailContents = createString("detailContents");

    public final StringPath detailImage = createString("detailImage");

    public final NumberPath<Long> dno = createNumber("dno", Long.class);

    public QDetailSchedule(String variable) {
        super(DetailSchedule.class, forVariable(variable));
    }

    public QDetailSchedule(Path<? extends DetailSchedule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDetailSchedule(PathMetadata metadata) {
        super(DetailSchedule.class, metadata);
    }

}

