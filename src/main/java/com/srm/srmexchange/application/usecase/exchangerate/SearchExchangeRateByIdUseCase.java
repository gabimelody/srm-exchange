package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.application.mapper.ExchangeRateEntityMapper;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.in.exchangerate.SearchExchangeRateByIdPort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByIdExchangeRatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchExchangeRateByIdUseCase implements SearchExchangeRateByIdPort {

    private final ExchangeRateEntityMapper exchangeRateEntityMapper;
    private final FindByIdExchangeRatePort findByIdExchangeRatePort;

    @Override
    public ExchangeRateOutbound execute(UUID id) {
        log.info("Searching exchange rate by id: {}", id);
        return findByIdExchangeRatePort.execute(id)
                .map(this::mapToOutbound)
                .orElse(null);
    }

    private ExchangeRateOutbound mapToOutbound(ExchangeRateEntity entity) {
        log.info("Exchange rate found: {}", entity);
        return exchangeRateEntityMapper.toOutbound(entity);
    }

}