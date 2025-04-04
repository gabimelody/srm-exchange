package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.DeleteExchangeRatePort;
import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteExchangeRateAdapter implements DeleteExchangeRatePort {

    private final ExchangeRateRepository repository;

    @Override
    public void execute(ExchangeRateEntity entity) {
        log.info("Deleting entity: {}", entity);
        repository.delete(entity);
        log.debug("Entity deleted");
    }

}