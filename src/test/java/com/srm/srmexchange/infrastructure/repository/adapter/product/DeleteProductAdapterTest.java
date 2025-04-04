package com.srm.srmexchange.infrastructure.repository.adapter.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith({MockitoExtension.class})
public class DeleteProductAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductAdapter deleteProductAdapter;

    @Test
    @DisplayName("Execute should delete the product")
    void execute_shouldDelete() {
        // Given
        ProductEntity entity = ProductEntity.builder()
                .name("test")
                .build();

        // When
        doNothing().when(productRepository).delete(entity);

        // Then
        assertDoesNotThrow(() -> deleteProductAdapter.execute(entity));
    }

}