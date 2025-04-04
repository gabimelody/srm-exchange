package com.srm.srmexchange.infrastructure.repository.adapter.exchangerate;

import com.srm.srmexchange.infrastructure.repository.ExchangeRateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FindByCoinsExchangeRatePortAdapterTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExistsByPairExchangeRatePortAdapter existsByPairExchangeRatePortAdapter;

    @Test
    @DisplayName("Execute should save the exchange rate")
    void execute_shouldSave() {
        // Given
        UUID id = UUID.randomUUID();
        UUID idCoinTo = UUID.randomUUID();
        UUID idCoinFrom = UUID.randomUUID();

        // When
        when(exchangeRateRepository.existsByPair(id, idCoinTo, idCoinFrom)).thenReturn(true);

        // Then
        assertEquals(true, existsByPairExchangeRatePortAdapter.execute(id, idCoinTo, idCoinFrom));
    }

}