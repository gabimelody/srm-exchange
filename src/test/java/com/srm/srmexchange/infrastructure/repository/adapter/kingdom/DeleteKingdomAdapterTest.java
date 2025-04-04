package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith({MockitoExtension.class})
public class DeleteKingdomAdapterTest {

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private DeleteKingdomAdapter deleteKingdomAdapter;

    @Test
    @DisplayName("Execute should delete the kingdom")
    void execute_shouldDelete() {
        // Given
        KingdomEntity entity = KingdomEntity.builder()
                .name("test")
                .build();

        // When
        doNothing().when(kingdomRepository).delete(entity);

        // Then
        assertDoesNotThrow(() -> deleteKingdomAdapter.execute(entity));
    }

}