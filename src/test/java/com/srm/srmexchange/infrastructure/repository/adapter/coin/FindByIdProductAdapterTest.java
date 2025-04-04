package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import com.srm.srmexchange.infrastructure.repository.adapter.product.FindByIdProductAdapter;
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
public class FindByIdProductAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private FindByIdProductAdapter findByIdProductAdapter;

    @Test
    @DisplayName("Execute should find product by id")
    void execute_shouldFindById() {
        // Given
        UUID uuid = UUID.randomUUID();
        Optional<ProductEntity> optionalEntity = Optional.of(ProductEntity.builder()
                .name("test")
                .build());

        // When
        when(productRepository.findById(uuid)).thenReturn(optionalEntity);

        // Then
        assertEquals(optionalEntity, findByIdProductAdapter.execute(uuid));
    }

}