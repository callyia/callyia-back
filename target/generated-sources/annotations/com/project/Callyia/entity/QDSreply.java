package com.project.Callyia.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDSreply is a Querydsl query type for DSreply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDSreply extends EntityPathBase<DSreply> {

    private static final long serialVersionUID = 1953262701L;

    public static final QDSreply dSreply = new QDSreply("dSreply");

    public final SimplePath<Member> member = createSimple("member", Member.class);

    public final StringPath replyContents = createString("replyContents");

    public final NumberPath<Long> rno = createNumber("rno", Long.class);

    public QDSreply(String variable) {
        super(DSreply.class, forVariable(variable));
    }

    public QDSreply(Path<? extends DSreply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDSreply(PathMetadata metadata) {
        super(DSreply.class, metadata);
    }

}

