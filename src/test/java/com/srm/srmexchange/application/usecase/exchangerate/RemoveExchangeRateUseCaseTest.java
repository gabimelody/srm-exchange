package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.port.out.exchangerate.DeleteExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByIdExchangeRatePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RemoveExchangeRateUseCaseTest {

    @Mock
    private DeleteExchangeRatePort deleteExchangeRatePort;

    @Mock
    private FindByIdExchangeRatePort findByIdExchangeRatePort;

    @InjectMocks
    private RemoveExchangeRateUseCase removeExchangeRateUseCase;

    @Test
    @DisplayName("Execute should remove exchange rate")
    void execute_shouldRemove() {
        // Given
        UUID id = UUID.randomUUID();
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .vlRate(BigDecimal.ONE)
                .build();

        // When
        when(findByIdExchangeRatePort.execute(id)).thenReturn(Optional.of(entity));
        doNothing().when(deleteExchangeRatePort).execute(entity);

        // Then
        assertTrue(removeExchangeRateUseCase.execute(id));
    }

    @Test
    @DisplayName("Execute should not remove exchange rate")
    void execute_shouldNotRemove() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(findByIdExchangeRatePort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertFalse(removeExchangeRateUseCase.execute(id));
    }

}