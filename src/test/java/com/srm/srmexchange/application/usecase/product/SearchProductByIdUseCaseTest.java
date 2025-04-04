package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
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
public class SearchProductByIdUseCaseTest {

    @Mock
    private ProductEntityMapper productEntityMapper;

    @Mock
    private FindByIdProductPort findByIdProductPort;

    @InjectMocks
    private SearchProductByIdUseCase searchProductByIdUseCase;

    @Test
    @DisplayName("Execute should search product by id")
    void execute_shouldSearchById() {
        // Given
        UUID id = UUID.randomUUID();
        ProductInbound inbound = ProductInbound.builder()
                .name("test")
                .build();

        ProductEntity entity = ProductEntity.builder()
                .id(id)
                .name(inbound.getName())
                .build();

        ProductOutbound outbound = ProductOutbound.builder()
                .id(id)
                .name(inbound.getName())
                .build();

        // When
        when(findByIdProductPort.execute(id)).thenReturn(Optional.of(entity));
        when(productEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(outbound, searchProductByIdUseCase.execute(id));
    }

}