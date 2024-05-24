package com.example.CampaignMarketing.domain.campaign.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCampaign is a Querydsl query type for Campaign
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCampaign extends EntityPathBase<Campaign> {

    private static final long serialVersionUID = 1261475614L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCampaign campaign = new QCampaign("campaign");

    public final com.example.CampaignMarketing.global.entity.QTimestamped _super = new com.example.CampaignMarketing.global.entity.QTimestamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> imageUrls = this.<String, StringPath>createList("imageUrls", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> keywords = this.<String, StringPath>createList("keywords", String.class, StringPath.class, PathInits.DIRECT2);

    public final com.example.CampaignMarketing.domain.market.entity.QMarket market;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QCampaign(String variable) {
        this(Campaign.class, forVariable(variable), INITS);
    }

    public QCampaign(Path<? extends Campaign> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCampaign(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCampaign(PathMetadata metadata, PathInits inits) {
        this(Campaign.class, metadata, inits);
    }

    public QCampaign(Class<? extends Campaign> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.market = inits.isInitialized("market") ? new com.example.CampaignMarketing.domain.market.entity.QMarket(forProperty("market"), inits.get("market")) : null;
    }

}

