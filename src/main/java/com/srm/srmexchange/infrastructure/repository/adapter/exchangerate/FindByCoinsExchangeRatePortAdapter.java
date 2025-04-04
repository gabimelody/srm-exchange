package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByCoinsExchangeRatePort;
import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindByCoinsExchangeRatePortAdapter implements FindByCoinsExchangeRatePort {

    private final ExchangeRateRepository repository;

    @Override
    public Optional<ExchangeRateEntity> execute(UUID idCoinFirst, UUID idCoinSecond) {
        return repository.findByCoins(idCoinFirst, idCoinSecond);
    }

}