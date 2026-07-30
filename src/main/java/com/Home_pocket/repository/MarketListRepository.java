package com.Home_pocket.repository;

import com.Home_pocket.model.MarketList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketListRepository extends JpaRepository<MarketList, Long> {
}
