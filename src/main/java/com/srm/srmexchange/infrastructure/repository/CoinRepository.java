package com.srm.srmexchange.infrastructure.repository;

import com.srm.srmexchange.domain.domain.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoinRepository extends JpaRepository<CoinEntity, UUID> {

}