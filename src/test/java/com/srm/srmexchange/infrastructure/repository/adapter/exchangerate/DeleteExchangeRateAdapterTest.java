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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith({MockitoExtension.class})
public class DeleteExchangeRateAdapterTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private DeleteExchangeRateAdapter deleteExchangeRateAdapter;

    @Test
    @DisplayName("Execute should delete the exchange rate")
    void execute_shouldDelete() {
        // Given
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .vlRate(BigDecimal.ONE)
                .build();

        // When
        doNothing().when(exchangeRateRepository).delete(entity);

        // Then
        assertDoesNotThrow(() -> deleteExchangeRateAdapter.execute(entity));
    }

}