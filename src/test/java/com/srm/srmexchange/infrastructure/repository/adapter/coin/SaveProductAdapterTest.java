package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import com.srm.srmexchange.infrastructure.repository.adapter.product.SaveProductAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SaveProductAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SaveProductAdapter saveProductAdapter;

    @Test
    @DisplayName("Execute should save the product")
    void execute_shouldSave() {
        // Given
        ProductEntity entity = ProductEntity.builder()
                .name("test")
                .build();

        // When
        when(productRepository.save(entity)).thenReturn(entity);

        // Then
        assertEquals(entity, saveProductAdapter.execute(entity));
    }

}