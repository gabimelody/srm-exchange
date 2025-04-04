package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.FindAllProductPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SearchProductUseCaseTest {

    @Mock
    private FindAllProductPort findAllProductPort;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private SearchProductUseCase searchProductUseCase;

    @Test
    @DisplayName("Execute should search all products")
    void execute_shouldSearch() {
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
        when(findAllProductPort.execute()).thenReturn(List.of(entity));
        when(productEntityMapper.toOutbound(entity)).thenReturn(outbound);

        // Then
        assertEquals(List.of(outbound), searchProductUseCase.execute());
    }

}