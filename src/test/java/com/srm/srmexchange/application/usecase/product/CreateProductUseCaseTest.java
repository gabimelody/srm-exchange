package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.exception.CoinNotFoundException;
import com.srm.srmexchange.domain.exception.KingdomNotFoundException;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
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
public class CreateProductUseCaseTest {

    @Mock
    private SaveProductPort saveProductPort;

    @Mock
    private FindByIdCoinPort findByIdCoinPort;

    @Mock
    private FindByIdKingdomPort findByIdKingdomPort;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @Test
    @DisplayName("Execute should create product")
    void execute_shouldCreate() {
        // Given
        ProductInbound inbound = getProductInbound();
        ProductEntity entity = getProductEntity(inbound);
        ProductOutbound outbound = getProductOutbound(inbound);

        // When
        when(productEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(findByIdCoinPort.execute(inbound.getIdCoinBase())).thenReturn(Optional.of(entity.getCoinBase()));
        when(findByIdKingdomPort.execute(inbound.getIdKingdom())).thenReturn(Optional.of(entity.getKingdom()));
        when(saveProductPort.execute(entity)).thenReturn(entity);
        when(productEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, createProductUseCase.execute(inbound));
    }

    @Test
    @DisplayName("Execute should throw KingdomNotFoundException")
    void execute_shouldThrowKingdomNotFoundException() {
        // Given
        ProductInbound inbound = getProductInbound();
        ProductEntity entity = getProductEntity(inbound);

        // When
        when(productEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(findByIdCoinPort.execute(inbound.getIdCoinBase())).thenReturn(Optional.of(entity.getCoinBase()));
        when(findByIdKingdomPort.execute(inbound.getIdKingdom())).thenReturn(Optional.empty());

        // Then
        assertThrows(KingdomNotFoundException.class, () -> createProductUseCase.execute(inbound));
    }

    @Test
    @DisplayName("Execute should throw CoinNotFoundException")
    void execute_shouldThrowCoinNotFoundException() {
        // Given
        ProductInbound inbound = getProductInbound();
        ProductEntity entity = getProductEntity(inbound);

        // When
        when(productEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(findByIdCoinPort.execute(inbound.getIdCoinBase())).thenReturn(Optional.empty());

        // Then
        assertThrows(CoinNotFoundException.class, () -> createProductUseCase.execute(inbound));
    }

    ProductInbound getProductInbound() {
        return ProductInbound.builder()
                .name("test")
                .vlUnit(BigDecimal.ONE)
                .idKingdom(UUID.randomUUID())
                .idCoinBase(UUID.randomUUID())
                .build();
    }

    ProductEntity getProductEntity(ProductInbound inbound) {
        return ProductEntity.builder()
                .name(inbound.getName())
                .vlUnit(BigDecimal.ONE)
                .kingdom(getKingdomEntity(inbound))
                .coinBase(getCoinEntity(inbound))
                .build();
    }

    CoinEntity getCoinEntity(ProductInbound inbound) {
        return CoinEntity.builder()
                .id(inbound.getIdCoinBase())
                .build();
    }

    KingdomEntity getKingdomEntity(ProductInbound inbound) {
        return KingdomEntity.builder()
                .id(inbound.getIdKingdom())
                .build();
    }

    ProductOutbound getProductOutbound(ProductInbound inbound) {
        return ProductOutbound.builder()
                .name(inbound.getName())
                .vlUnit(inbound.getVlUnit())
                .coinBase(getCoinOutbound(inbound))
                .kingdom(getKingdomOutbound(inbound))
                .build();
    }

    CoinOutbound getCoinOutbound(ProductInbound inbound) {
        return CoinOutbound.builder()
                .id(inbound.getIdCoinBase())
                .build();
    }

    KingdomOutbound getKingdomOutbound(ProductInbound inbound) {
        return KingdomOutbound.builder()
                .id(inbound.getIdKingdom())
                .build();
    }

}