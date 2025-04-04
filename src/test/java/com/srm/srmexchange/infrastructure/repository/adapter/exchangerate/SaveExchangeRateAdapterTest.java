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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SaveExchangeRateAdapterTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private SaveExchangeRateAdapter saveExchangeRateAdapter;

    @Test
    @DisplayName("Execute should save the exchange rate")
    void execute_shouldSave() {
        // Given
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .vlRate(BigDecimal.ONE)
                .build();

        // When
        when(exchangeRateRepository.save(entity)).thenReturn(entity);

        // Then
        assertEquals(entity, saveExchangeRateAdapter.execute(entity));
    }

}