package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.SaveExchangeRatePort;
import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveExchangeRateAdapter implements SaveExchangeRatePort {

    private final ExchangeRateRepository repository;

    @Override
    public ExchangeRateEntity execute(ExchangeRateEntity entity) {
        log.info("Creating entity: {}", entity);
        entity = repository.save(entity);
        log.info("Entity created: {}", entity);

        return entity;
    }

}