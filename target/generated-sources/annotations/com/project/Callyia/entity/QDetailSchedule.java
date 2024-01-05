package com.project.Callyia.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDetailSchedule is a Querydsl query type for DetailSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDetailSchedule extends EntityPathBase<DetailSchedule> {

    private static final long serialVersionUID = -944124618L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDetailSchedule detailSchedule = new QDetailSchedule("detailSchedule");

    public final StringPath detailContents = createString("detailContents");

    public final StringPath detailImages = createString("detailImages");

    public final NumberPath<Long> dno = createNumber("dno", Long.class);

    public final QSchedule schedule;

    public QDetailSchedule(String variable) {
        this(DetailSchedule.class, forVariable(variable), INITS);
    }

    public QDetailSchedule(Path<? extends DetailSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDetailSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDetailSchedule(PathMetadata metadata, PathInits inits) {
        this(DetailSchedule.class, metadata, inits);
    }

    public QDetailSchedule(Class<? extends DetailSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
    }

}

