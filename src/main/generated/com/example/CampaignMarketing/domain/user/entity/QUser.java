package com.example.CampaignMarketing.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -366060652L;

    public static final QUser user = new QUser("user");

    public final com.example.CampaignMarketing.global.entity.QTimestamped _super = new com.example.CampaignMarketing.global.entity.QTimestamped(this);

    public final StringPath activityArea = createString("activityArea");

    public final StringPath bio = createString("bio");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final StringPath blogUrl = createString("blogUrl");

    public final ListPath<String, StringPath> cant_foods = this.<String, StringPath>createList("cant_foods", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> considerations = this.<String, StringPath>createList("considerations", String.class, StringPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final ListPath<String, StringPath> fav_foods = this.<String, StringPath>createList("fav_foods", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath job = createString("job");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath password = createString("password");

    public final StringPath provider = createString("provider");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

