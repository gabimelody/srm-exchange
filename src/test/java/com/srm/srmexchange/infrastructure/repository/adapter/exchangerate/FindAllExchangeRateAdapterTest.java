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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FindAllExchangeRateAdapterTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private FindAllExchangeRateAdapter findAllExchangeRateAdapter;

    @Test
    @DisplayName("Execute should find all exchange rates")
    void execute_shouldFindAll() {
        // Given
        List<ExchangeRateEntity> listOfEntity = List.of(ExchangeRateEntity.builder()
                .vlRate(BigDecimal.ONE)
                .build());

        // When
        when(exchangeRateRepository.findAll()).thenReturn(listOfEntity);

        // Then
        assertEquals(listOfEntity, findAllExchangeRateAdapter.execute());
    }

}