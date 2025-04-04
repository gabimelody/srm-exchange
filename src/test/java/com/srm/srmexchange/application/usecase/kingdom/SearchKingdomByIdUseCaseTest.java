package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SearchKingdomByIdUseCaseTest {

    @Mock
    private KingdomEntityMapper kingdomEntityMapper;

    @Mock
    private FindByIdKingdomPort findByIdKingdomPort;

    @InjectMocks
    private SearchKingdomByIdUseCase searchKingdomByIdUseCase;

    @Test
    @DisplayName("Execute should search kingdom by id")
    void execute_shouldSearchById() {
        // Given
        UUID id = UUID.randomUUID();
        KingdomInbound inbound = KingdomInbound.builder()
                .name("test")
                .build();

        KingdomEntity entity = KingdomEntity.builder()
                .id(id)
                .name(inbound.getName())
                .build();

        KingdomOutbound outbound = KingdomOutbound.builder()
                .id(id)
                .name(inbound.getName())
                .build();

        // When
        when(findByIdKingdomPort.execute(id)).thenReturn(Optional.of(entity));
        when(kingdomEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, searchKingdomByIdUseCase.execute(id));
    }

}