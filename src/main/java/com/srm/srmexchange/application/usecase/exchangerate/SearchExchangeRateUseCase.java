package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.application.mapper.ExchangeRateEntityMapper;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.in.exchangerate.SearchExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindAllExchangeRatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchExchangeRateUseCase implements SearchExchangeRatePort {

    private final FindAllExchangeRatePort findAllExchangeRatePort;
    private final ExchangeRateEntityMapper exchangeRateEntityMapper;

    @Override
    public List<ExchangeRateOutbound> execute() {
        log.info("Searching all exchange rates");
        return findAllExchangeRatePort.execute().stream()
                .map(this::mapToOutbound)
                .toList();
    }

    private ExchangeRateOutbound mapToOutbound(ExchangeRateEntity entity) {
        log.info("Exchange rate found: {}", entity);
        return exchangeRateEntityMapper.toOutbound(entity);
    }

}