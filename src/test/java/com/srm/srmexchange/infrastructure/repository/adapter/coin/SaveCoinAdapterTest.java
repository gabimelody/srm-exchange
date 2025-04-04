package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SaveCoinAdapterTest {

    @Mock
    private CoinRepository coinRepository;

    @InjectMocks
    private SaveCoinAdapter saveCoinAdapter;

    @Test
    @DisplayName("Execute should save the coin")
    void execute_shouldSave() {
        // Given
        CoinEntity entity = CoinEntity.builder()
                .name("test")
                .build();

        // When
        when(coinRepository.save(entity)).thenReturn(entity);

        // Then
        assertEquals(entity, saveCoinAdapter.execute(entity));
    }

}