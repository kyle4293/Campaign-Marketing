package com.example.CampaignMarketing.domain.campaign.repository;

import com.example.CampaignMarketing.domain.campaign.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>, QuerydslPredicateExecutor<Campaign> {

    Page<Campaign> findAll(Pageable pageable);

}
