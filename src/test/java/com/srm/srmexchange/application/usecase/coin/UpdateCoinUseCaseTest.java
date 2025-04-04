package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.coin.SaveCoinPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class UpdateCoinUseCaseTest {

    @Mock
    private SaveCoinPort saveCoinPort;

    @Mock
    private FindByIdCoinPort findByIdCoinPort;

    @Mock
    private CoinEntityMapper coinEntityMapper;

    @InjectMocks
    private UpdateCoinUseCase updateCoinUseCase;

    @Test
    @DisplayName("Execute should update coin")
    void execute_shouldUpdate() {
        // Given
        UUID id = UUID.randomUUID();
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
        when(findByIdCoinPort.execute(id)).thenReturn(Optional.of(entity));
        when(saveCoinPort.execute(entity)).thenReturn(entity);
        when(coinEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, updateCoinUseCase.execute(id, inbound));
    }

    @Test
    @DisplayName("Execute should not update coin")
    void execute_shouldNotUpdate() {
        // Given
        UUID id = UUID.randomUUID();
        CoinInbound inbound = CoinInbound.builder()
                .name("test")
                .build();

        // When
        when(findByIdCoinPort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertNull(updateCoinUseCase.execute(id, inbound));
    }

}