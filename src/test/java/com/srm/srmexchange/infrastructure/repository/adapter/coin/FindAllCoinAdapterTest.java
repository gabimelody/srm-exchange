package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import com.srm.srmexchange.infrastructure.repository.adapter.coin.FindAllCoinAdapter;
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
public class FindAllCoinAdapterTest {

    @Mock
    private CoinRepository coinRepository;

    @InjectMocks
    private FindAllCoinAdapter findAllCoinAdapter;

    @Test
    @DisplayName("Execute should find all coins")
    void execute_shouldFindAll() {
        // Given
        List<CoinEntity> listOfEntity = List.of(CoinEntity.builder()
                .name("test")
                .build());

        // When
        when(coinRepository.findAll()).thenReturn(listOfEntity);

        // Then
        assertEquals(listOfEntity, findAllCoinAdapter.execute());
    }

}