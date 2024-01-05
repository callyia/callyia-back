package com.project.Callyia.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDSreply is a Querydsl query type for DSreply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDSreply extends EntityPathBase<DSreply> {

    private static final long serialVersionUID = 1953262701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDSreply dSreply = new QDSreply("dSreply");

    public final QDetailSchedule detailSchedule;

    public final StringPath replyContents = createString("replyContents");

    public final NumberPath<Long> rno = createNumber("rno", Long.class);

    public QDSreply(String variable) {
        this(DSreply.class, forVariable(variable), INITS);
    }

    public QDSreply(Path<? extends DSreply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDSreply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDSreply(PathMetadata metadata, PathInits inits) {
        this(DSreply.class, metadata, inits);
    }

    public QDSreply(Class<? extends DSreply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.detailSchedule = inits.isInitialized("detailSchedule") ? new QDetailSchedule(forProperty("detailSchedule"), inits.get("detailSchedule")) : null;
    }

}

