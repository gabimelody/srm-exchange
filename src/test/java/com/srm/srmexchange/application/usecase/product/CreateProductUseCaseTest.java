package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class CreateProductUseCaseTest {

    @Mock
    private SaveProductPort saveProductPort;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @Test
    @DisplayName("Execute should create product")
    void execute_shouldCreate() {
        // Given
        ProductInbound inbound = ProductInbound.builder()
                .name("test")
                .build();

        ProductEntity entity = ProductEntity.builder()
                .name(inbound.getName())
                .build();

        ProductOutbound outbound = ProductOutbound.builder()
                .name(inbound.getName())
                .build();

        // When
        when(productEntityMapper.toEntity(inbound)).thenReturn(entity);
        when(saveProductPort.execute(entity)).thenReturn(entity);
        when(productEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, createProductUseCase.execute(inbound));
    }

}