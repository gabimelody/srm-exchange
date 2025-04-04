package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FindByIdExchangeRateAdapterTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private FindByIdExchangeRateAdapter findByIdExchangeRateAdapter;

    @Test
    @DisplayName("Execute should find exchange rate by id")
    void execute_shouldFindById() {
        // Given
        UUID uuid = UUID.randomUUID();
        Optional<ExchangeRateEntity> optionalEntity = Optional.of(ExchangeRateEntity.builder()
                .vlRate(BigDecimal.ONE)
                .build());

        // When
        when(exchangeRateRepository.findById(uuid)).thenReturn(optionalEntity);

        // Then
        assertEquals(optionalEntity, findByIdExchangeRateAdapter.execute(uuid));
    }

}