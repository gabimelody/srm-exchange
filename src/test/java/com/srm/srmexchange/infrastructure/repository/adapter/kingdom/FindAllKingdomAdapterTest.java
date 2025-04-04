package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FindAllKingdomAdapterTest {

    @Mock
    private KingdomRepository kingdomRepository;

    @InjectMocks
    private FindAllKingdomAdapter findAllKingdomAdapter;

    @Test
    @DisplayName("Execute should find all kingdoms")
    void execute_shouldFindAll() {
        // Given
        List<KingdomEntity> listOfEntity = List.of(KingdomEntity.builder()
                .name("test")
                .build());

        // When
        when(kingdomRepository.findAll()).thenReturn(listOfEntity);

        // Then
        assertEquals(listOfEntity, findAllKingdomAdapter.execute());
    }

}