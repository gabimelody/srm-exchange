package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.application.mapper.ExchangeRateEntityMapper;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.FindAllExchangeRatePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SearchExchangeRateUseCaseTest {

    @Mock
    private FindAllExchangeRatePort findAllExchangeRatePort;

    @Mock
    private ExchangeRateEntityMapper exchangeRateEntityMapper;

    @InjectMocks
    private SearchExchangeRateUseCase searchExchangeRateUseCase;

    @Test
    @DisplayName("Execute should search all exchange rates")
    void execute_shouldSearch() {
        // Given
        UUID id = UUID.randomUUID();
        ExchangeRateInbound inbound = ExchangeRateInbound.builder()
                .vlRate(BigDecimal.ONE)
                .build();

        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .id(id)
                .vlRate(inbound.getVlRate())
                .build();

        ExchangeRateOutbound outbound = ExchangeRateOutbound.builder()
                .id(id)
                .vlRate(inbound.getVlRate())
                .build();

        // When
        when(findAllExchangeRatePort.execute()).thenReturn(List.of(entity));
        when(exchangeRateEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(List.of(outbound), searchExchangeRateUseCase.execute());
    }

}