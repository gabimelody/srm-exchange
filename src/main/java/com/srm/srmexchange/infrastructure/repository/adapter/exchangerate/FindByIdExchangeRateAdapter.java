package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByIdExchangeRatePort;
import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindByIdExchangeRateAdapter implements FindByIdExchangeRatePort {

    private final ExchangeRateRepository repository;

    @Override
    public Optional<ExchangeRateEntity> execute(UUID id) {
        log.debug("Finding entity by id: {}", id);
        Optional<ExchangeRateEntity> optional = repository.findById(id);
        log.debug("Entity found: {}", optional.isPresent());

        return optional;
    }

}