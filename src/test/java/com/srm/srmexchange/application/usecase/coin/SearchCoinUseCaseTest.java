package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.FindAllCoinPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SearchCoinUseCaseTest {

    @Mock
    private FindAllCoinPort findAllCoinPort;

    @Mock
    private CoinEntityMapper coinEntityMapper;

    @InjectMocks
    private SearchCoinUseCase searchCoinUseCase;

    @Test
    @DisplayName("Execute should search all coins")
    void execute_shouldSearch() {
        // Given
        UUID id = UUID.randomUUID();
        CoinInbound inbound = CoinInbound.builder()
                .name("test")
                .build();

        CoinEntity entity = CoinEntity.builder()
                .id(id)
                .name(inbound.getName())
                .build();

        CoinOutbound outbound = CoinOutbound.builder()
                .id(id)
                .name(inbound.getName())
                .build();

        // When
        when(findAllCoinPort.execute()).thenReturn(List.of(entity));
        when(coinEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(List.of(outbound), searchCoinUseCase.execute());
    }

}