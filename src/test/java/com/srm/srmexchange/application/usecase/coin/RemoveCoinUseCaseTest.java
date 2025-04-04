package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.DeleteCoinPort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
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
public class RemoveCoinUseCaseTest {

    @Mock
    private DeleteCoinPort deleteCoinPort;

    @Mock
    private FindByIdCoinPort findByIdCoinPort;

    @InjectMocks
    private RemoveCoinUseCase removeCoinUseCase;

    @Test
    @DisplayName("Execute should remove coin")
    void execute_shouldRemove() {
        // Given
        UUID id = UUID.randomUUID();
        CoinEntity entity = CoinEntity.builder()
                .name("test")
                .build();

        // When
        when(findByIdCoinPort.execute(id)).thenReturn(Optional.of(entity));
        doNothing().when(deleteCoinPort).execute(entity);

        // Then
        assertTrue(removeCoinUseCase.execute(id));
    }

    @Test
    @DisplayName("Execute should not remove coin")
    void execute_shouldNotRemove() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(findByIdCoinPort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertFalse(removeCoinUseCase.execute(id));
    }

}