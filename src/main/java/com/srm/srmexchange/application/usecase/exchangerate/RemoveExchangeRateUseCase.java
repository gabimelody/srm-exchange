package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.in.exchangerate.RemoveExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.DeleteExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByIdExchangeRatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class RemoveExchangeRateUseCase implements RemoveExchangeRatePort {

    private final DeleteExchangeRatePort deleteExchangeRatePort;
    private final FindByIdExchangeRatePort findByIdExchangeRatePort;

    @Override
    @Transactional
    public boolean execute(UUID id) {
        log.info("Removing exchange rate by id: {}", id);
        return findByIdExchangeRatePort.execute(id)
                .map(this::remove)
                .orElse(false);
    }

    private boolean remove(ExchangeRateEntity exchangeRateEntity) {
        deleteExchangeRatePort.execute(exchangeRateEntity);
        return true;
    }

}