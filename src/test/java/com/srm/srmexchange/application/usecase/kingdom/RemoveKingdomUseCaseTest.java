package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.DeleteKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RemoveKingdomUseCaseTest {

    @Mock
    private DeleteKingdomPort deleteKingdomPort;

    @Mock
    private FindByIdKingdomPort findByIdKingdomPort;

    @InjectMocks
    private RemoveKingdomUseCase removeKingdomUseCase;

    @Test
    @DisplayName("Execute should remove kingdom")
    void execute_shouldRemove() {
        // Given
        UUID id = UUID.randomUUID();
        KingdomEntity entity = KingdomEntity.builder()
                .name("test")
                .build();

        // When
        when(findByIdKingdomPort.execute(id)).thenReturn(Optional.of(entity));
        doNothing().when(deleteKingdomPort).execute(entity);

        // Then
        assertTrue(removeKingdomUseCase.execute(id));
    }

    @Test
    @DisplayName("Execute should not remove kingdom")
    void execute_shouldNotRemove() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(findByIdKingdomPort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertFalse(removeKingdomUseCase.execute(id));
    }

}