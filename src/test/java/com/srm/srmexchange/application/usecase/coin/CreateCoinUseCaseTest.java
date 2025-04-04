package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.SaveCoinPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class CreateCoinUseCaseTest {

    @Mock
    private SaveCoinPort saveCoinPort;

    @Mock
    private CoinEntityMapper coinEntityMapper;

    @InjectMocks
    private CreateCoinUseCase createCoinUseCase;

    @Test
    @DisplayName("Execute should create coin")
    void execute_shouldCreate() {
        // Given
        CoinInbound inbound = CoinInbound.builder()
                .name("test")
                .build();

        CoinEntity entity = CoinEntity.builder()
                .name(inbound.getName())
                .build();

        CoinOutbound outbound = CoinOutbound.builder()
                .name(inbound.getName())
                .build();

        // When
        when(coinEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(saveCoinPort.execute(entity)).thenReturn(entity);
        when(coinEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, createCoinUseCase.execute(inbound));
    }

}