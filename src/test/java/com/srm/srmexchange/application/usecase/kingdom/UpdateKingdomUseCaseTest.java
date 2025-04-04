package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.SaveKingdomPort;
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
public class UpdateKingdomUseCaseTest {

    @Mock
    private SaveKingdomPort saveKingdomPort;

    @Mock
    private FindByIdKingdomPort findByIdKingdomPort;

    @Mock
    private KingdomEntityMapper kingdomEntityMapper;

    @InjectMocks
    private UpdateKingdomUseCase updateKingdomUseCase;

    @Test
    @DisplayName("Execute should update kingdom")
    void execute_shouldUpdate() {
        // Given
        UUID id = UUID.randomUUID();
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
        when(findByIdKingdomPort.execute(id)).thenReturn(Optional.of(entity));
        when(saveKingdomPort.execute(entity)).thenReturn(entity);
        when(kingdomEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, updateKingdomUseCase.execute(id, inbound));
    }

    @Test
    @DisplayName("Execute should not update kingdom")
    void execute_shouldNotUpdate() {
        // Given
        UUID id = UUID.randomUUID();
        KingdomInbound inbound = KingdomInbound.builder()
                .name("test")
                .build();

        // When
        when(findByIdKingdomPort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertNull(updateKingdomUseCase.execute(id, inbound));
    }

}