package com.srm.srmexchange.infrastructure.repository;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, UUID> {

    @Query(value = " SELECT e " +
            "          FROM ExchangeRateEntity e " +
            "         WHERE (e.coinTo.id = :idCoinFirst OR e.coinFrom.id = :idCoinFirst) " +
            "           AND (e.coinTo.id = :idCoinSecond OR e.coinFrom.id = :idCoinSecond) " +
            "      ORDER BY e.id " +
            "         LIMIT 1 OFFSET 0 ")
    Optional<ExchangeRateEntity> findByCoins(UUID idCoinFirst, UUID idCoinSecond);

}