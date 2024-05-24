package com.example.CampaignMarketing.domain.market.repository;

import com.example.CampaignMarketing.domain.market.entity.Market;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long>, QuerydslPredicateExecutor<Market> {

    Page<Market> findByUser(User user, Pageable pageable);

    Page<Market> findAll(Pageable pageable);

}
