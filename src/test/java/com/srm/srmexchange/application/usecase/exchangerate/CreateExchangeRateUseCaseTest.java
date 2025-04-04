package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.application.mapper.ExchangeRateEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.exception.CoinNotFoundException;
import com.srm.srmexchange.domain.exception.CoinsAreTheSameException;
import com.srm.srmexchange.domain.exception.ExchangeRateAlreadyExistException;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByCoinsExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.SaveExchangeRatePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class CreateExchangeRateUseCaseTest {

    @Mock
    private FindByIdCoinPort findByIdCoinPort;

    @Mock
    private SaveExchangeRatePort saveExchangeRatePort;

    @Mock
    private ExchangeRateEntityMapper exchangeRateEntityMapper;

    @Mock
    private FindByCoinsExchangeRatePort findByCoinsExchangeRatePort;

    @InjectMocks
    private CreateExchangeRateUseCase createExchangeRateUseCase;

    @Test
    @DisplayName("Execute should create exchange rate")
    void execute_shouldCreate() {
        // Given
        ExchangeRateInbound inbound = getExchangeRateInbound();
        ExchangeRateEntity entity = getExchangeRateEntity(inbound);
        ExchangeRateOutbound outbound = getExchangeRateOutbound(inbound);

        // When
        when(findByCoinsExchangeRatePort.execute(null, inbound.getIdCoinTo(), inbound.getIdCoinFrom())).thenReturn(false);
        when(exchangeRateEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(findByIdCoinPort.execute(inbound.getIdCoinTo())).thenReturn(Optional.of(entity.getCoinTo()));
        when(findByIdCoinPort.execute(inbound.getIdCoinFrom())).thenReturn(Optional.of(entity.getCoinFrom()));
        when(saveExchangeRatePort.execute(entity)).thenReturn(entity);
        when(exchangeRateEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, createExchangeRateUseCase.execute(inbound));
    }

    @Test
    @DisplayName("Execute should throw CoinsAreTheSameException")
    void execute_shouldThrowCoinsAreTheSameException() {
        // Given
        ExchangeRateInbound inbound = getExchangeRateInbound();
        inbound.setIdCoinTo(inbound.getIdCoinFrom());

        // Then
        assertThrows(CoinsAreTheSameException.class, () -> createExchangeRateUseCase.execute(inbound));
    }

    @Test
    @DisplayName("Execute should throw ExchangeRateAlreadyExistException")
    void execute_shouldThrowExchangeRateAlreadyExistException() {
        // Given
        ExchangeRateInbound inbound = getExchangeRateInbound();

        // When
        when(findByCoinsExchangeRatePort.execute(null, inbound.getIdCoinTo(), inbound.getIdCoinFrom())).thenReturn(true);

        // Then
        assertThrows(ExchangeRateAlreadyExistException.class, () -> createExchangeRateUseCase.execute(inbound));
    }

    @Test
    @DisplayName("Execute should throw CoinNotFoundException for coinTo")
    void execute_shouldThrowCoinNotFoundException1() {
        // Given
        ExchangeRateInbound inbound = getExchangeRateInbound();
        ExchangeRateEntity entity = getExchangeRateEntity(inbound);

        // When
        when(exchangeRateEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(findByIdCoinPort.execute(inbound.getIdCoinTo())).thenReturn(Optional.empty());

        // Then
        assertThrows(CoinNotFoundException.class, () -> createExchangeRateUseCase.execute(inbound));
    }

    @Test
    @DisplayName("Execute should throw CoinNotFoundException for coinFrom")
    void execute_shouldThrowCoinNotFoundException2() {
        // Given
        ExchangeRateInbound inbound = getExchangeRateInbound();
        ExchangeRateEntity entity = getExchangeRateEntity(inbound);

        // When
        when(exchangeRateEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(findByIdCoinPort.execute(inbound.getIdCoinTo())).thenReturn(Optional.of(entity.getCoinTo()));
        when(findByIdCoinPort.execute(inbound.getIdCoinFrom())).thenReturn(Optional.empty());

        // Then
        assertThrows(CoinNotFoundException.class, () -> createExchangeRateUseCase.execute(inbound));
    }

    ExchangeRateInbound getExchangeRateInbound() {
        return ExchangeRateInbound.builder()
                .vlRate(BigDecimal.ONE)
                .idCoinTo(UUID.randomUUID())
                .idCoinFrom(UUID.randomUUID())
                .build();
    }

    ExchangeRateEntity getExchangeRateEntity(ExchangeRateInbound inbound) {
        return ExchangeRateEntity.builder()
                .vlRate(inbound.getVlRate())
                .coinTo(getCoinEntity(inbound.getIdCoinTo()))
                .coinFrom(getCoinEntity(inbound.getIdCoinFrom()))
                .build();
    }

    CoinEntity getCoinEntity(UUID idCoin) {
        return CoinEntity.builder()
                .id(idCoin)
                .build();
    }

    ExchangeRateOutbound getExchangeRateOutbound(ExchangeRateInbound inbound) {
        return ExchangeRateOutbound.builder()
                .vlRate(inbound.getVlRate())
                .coinTo(getCoinOutbound(inbound.getIdCoinTo()))
                .coinFrom(getCoinOutbound(inbound.getIdCoinFrom()))
                .build();
    }

    CoinOutbound getCoinOutbound(UUID idCoin) {
        return CoinOutbound.builder()
                .id(idCoin)
                .build();
    }

}