package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.SaveKingdomPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class CreateKingdomUseCaseTest {

    @Mock
    private SaveKingdomPort saveKingdomPort;

    @Mock
    private KingdomEntityMapper kingdomEntityMapper;

    @InjectMocks
    private CreateKingdomUseCase createKingdomUseCase;

    @Test
    @DisplayName("Execute should create kingdom")
    void execute_shouldCreate() {
        // Given
        KingdomInbound inbound = KingdomInbound.builder()
                .name("test")
                .build();

        KingdomEntity entity = KingdomEntity.builder()
                .name(inbound.getName())
                .build();

        KingdomOutbound outbound = KingdomOutbound.builder()
                .name(inbound.getName())
                .build();

        // When
        when(kingdomEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(saveKingdomPort.execute(entity)).thenReturn(entity);
        when(kingdomEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, createKingdomUseCase.execute(inbound));
    }

}