package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import com.srm.srmexchange.infrastructure.repository.adapter.product.FindAllProductAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FindAllProductAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private FindAllProductAdapter findAllProductAdapter;

    @Test
    @DisplayName("Execute should find all products")
    void execute_shouldFindAll() {
        // Given
        List<ProductEntity> listOfEntity = List.of(ProductEntity.builder()
                .name("test")
                .build());

        // When
        when(productRepository.findAll()).thenReturn(listOfEntity);

        // Then
        assertEquals(listOfEntity, findAllProductAdapter.execute());
    }

}