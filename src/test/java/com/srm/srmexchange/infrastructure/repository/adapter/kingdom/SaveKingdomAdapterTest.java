package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SaveKingdomAdapterTest {

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private SaveKingdomAdapter saveKingdomAdapter;

    @Test
    @DisplayName("Execute should save the kingdom")
    void execute_shouldSave() {
        // Given
        KingdomEntity entity = KingdomEntity.builder()
                .name("test")
                .build();

        // When
        when(kingdomRepository.save(entity)).thenReturn(entity);

        // Then
        assertEquals(entity, saveKingdomAdapter.execute(entity));
    }

}