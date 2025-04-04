package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.exception.CoinNotFoundException;
import com.srm.srmexchange.domain.exception.KingdomNotFoundException;
import com.srm.srmexchange.domain.port.in.product.CreateProductPort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductPort {

    private final SaveProductPort saveProductPort;
    private final FindByIdCoinPort findByIdCoinPort;
    private final FindByIdKingdomPort findByIdKingdomPort;
    private final ProductEntityMapper productEntityMapper;

    @Override
    @Transactional
    public ProductOutbound execute(ProductInbound inbound) {
        ProductEntity entity = productEntityMapper.toEntity(inbound);

        entity.setCoinBase(findCoinById(inbound.getIdCoinBase()));
        entity.setKingdom(findKingdomById(inbound.getIdKingdom()));
        entity = saveProductPort.execute(entity);

        log.info("Product created: {}", entity);
        return productEntityMapper.toOutbound(entity);
    }

    private KingdomEntity findKingdomById(UUID idKingdom) {
        return findByIdKingdomPort.execute(idKingdom)
                .orElseThrow(KingdomNotFoundException::new);
    }

    private CoinEntity findCoinById(UUID idCoinBase) {
        return findByIdCoinPort.execute(idCoinBase)
                .orElseThrow(CoinNotFoundException::new);
    }

}