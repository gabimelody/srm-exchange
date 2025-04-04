package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.DeleteProductPort;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RemoveProductUseCaseTest {

    @Mock
    private DeleteProductPort deleteProductPort;

    @Mock
    private FindByIdProductPort findByIdProductPort;

    @InjectMocks
    private RemoveProductUseCase removeProductUseCase;

    @Test
    @DisplayName("Execute should remove product")
    void execute_shouldRemove() {
        // Given
        UUID id = UUID.randomUUID();
        ProductEntity entity = ProductEntity.builder()
                .name("test")
                .build();

        // When
        when(findByIdProductPort.execute(id)).thenReturn(Optional.of(entity));
        doNothing().when(deleteProductPort).execute(entity);

        // Then
        assertTrue(removeProductUseCase.execute(id));
    }

    @Test
    @DisplayName("Execute should not remove product")
    void execute_shouldNotRemove() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(findByIdProductPort.execute(id)).thenReturn(Optional.empty());

        // Then
        assertFalse(removeProductUseCase.execute(id));
    }

}