package com.homepocket.repository;

import com.homepocket.model.MarketList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketListRepository extends JpaRepository<MarketList, Long> {
}
