package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import com.srm.srmexchange.infrastructure.repository.adapter.coin.FindByIdCoinAdapter;
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
public class FindByIdCoinAdapterTest {

    @Mock
    private CoinRepository coinRepository;

    @InjectMocks
    private FindByIdCoinAdapter findByIdCoinAdapter;

    @Test
    @DisplayName("Execute should find coin by id")
    void execute_shouldFindById() {
        // Given
        UUID uuid = UUID.randomUUID();
        Optional<CoinEntity> optionalEntity = Optional.of(CoinEntity.builder()
                .name("test")
                .build());

        // When
        when(coinRepository.findById(uuid)).thenReturn(optionalEntity);

        // Then
        assertEquals(optionalEntity, findByIdCoinAdapter.execute(uuid));
    }

}