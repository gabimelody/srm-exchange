package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
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
public class FindByIdKingdomAdapterTest {

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private FindByIdKingdomAdapter findByIdKingdomAdapter;

    @Test
    @DisplayName("Execute should find kingdom by id")
    void execute_shouldFindById() {
        // Given
        UUID uuid = UUID.randomUUID();
        Optional<KingdomEntity> optionalEntity = Optional.of(KingdomEntity.builder()
                .name("test")
                .build());

        // When
        when(kingdomRepository.findById(uuid)).thenReturn(optionalEntity);

        // Then
        assertEquals(optionalEntity, findByIdKingdomAdapter.execute(uuid));
    }

}