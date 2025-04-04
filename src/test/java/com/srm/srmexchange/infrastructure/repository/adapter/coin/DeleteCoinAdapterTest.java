package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith({MockitoExtension.class})
public class DeleteCoinAdapterTest {

    @Mock
    private CoinRepository coinRepository;

    @InjectMocks
    private DeleteCoinAdapter deleteCoinAdapter;

    @Test
    @DisplayName("Execute should delete the coin")
    void execute_shouldDelete() {
        // Given
        CoinEntity entity = CoinEntity.builder()
                .name("test")
                .build();

        // When
        doNothing().when(coinRepository).delete(entity);

        // Then
        assertDoesNotThrow(() -> deleteCoinAdapter.execute(entity));
    }

}