package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class UpdateProductUseCaseTest {

    @Mock
    private SaveProductPort saveProductPort;

    @Mock
    private FindByIdProductPort findByIdProductPort;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private UpdateProductUseCase updateProductUseCase;

    @Test
    @DisplayName("Execute should update product")
    void execute_shouldUpdate() {
        // Given
        UUID id = UUID.randomUUID();
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
        when(findByIdProductPort.execute(id)).thenReturn(Optional.of(entity));
        when(saveProductPort.execute(entity)).thenReturn(entity);
        when(productEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, updateProductUseCase.execute(id, inbound));
    }

    @Test
    @DisplayName("Execute should not update product")
    void execute_shouldNotUpdate() {
        // Given
        UUID id = UUID.randomUUID();
        ProductInbound inbound = ProductInbound.builder()
                .name("test")
                .build();

        // When
        when(findByIdProductPort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertNull(updateProductUseCase.execute(id, inbound));
    }

}