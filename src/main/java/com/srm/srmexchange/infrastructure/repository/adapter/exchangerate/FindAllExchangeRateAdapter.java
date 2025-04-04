package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.FindAllExchangeRatePort;
import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllExchangeRateAdapter implements FindAllExchangeRatePort {

    private final ExchangeRateRepository repository;

    @Override
    public List<ExchangeRateEntity> execute() {
        log.info("Finding all entities");
        List<ExchangeRateEntity> list = repository.findAll();
        log.debug("Found {} entities", list.size());

        return list;
    }

}