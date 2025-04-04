package com.srm.srmexchange.application.usecase.transaction;

import com.srm.srmexchange.application.dto.transaction.TransactionInbound;
import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import com.srm.srmexchange.application.mapper.TransactionEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.domain.TransactionEntity;
import com.srm.srmexchange.domain.exception.CoinNotFoundException;
import com.srm.srmexchange.domain.exception.ExchangeRateNotFoundException;
import com.srm.srmexchange.domain.exception.ProductNotFoundException;
import com.srm.srmexchange.domain.port.in.transaction.CreateTransactionPort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByCoinsExchangeRatePort;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import com.srm.srmexchange.domain.port.out.transaction.SaveTransactionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateTransactionUseCase implements CreateTransactionPort {

    private final FindByIdCoinPort findByIdCoinPort;
    private final SaveTransactionPort saveTransactionPort;
    private final FindByIdProductPort findByIdProductPort;
    private final TransactionEntityMapper transactionEntityMapper;
    private final FindByCoinsExchangeRatePort findByCoinsExchangeRatePort;

    @Override
    @Transactional
    public TransactionOutbound execute(TransactionInbound inbound) {
        TransactionEntity entity = transactionEntityMapper.toEntity(inbound);
        setProductFields(inbound.getIdProduct(), entity);
        setValueFields(inbound.getIdCoinUsed(), entity.getCoinRequested().getId(), entity);

        entity.setDtTransaction(LocalDateTime.now());
        entity.setCoinUsed(findCoinById(inbound.getIdCoinUsed()));
        entity = saveTransactionPort.execute(entity);

        log.info("Transaction created: {}", entity);
        return transactionEntityMapper.toOutbound(entity);
    }

    private CoinEntity findCoinById(UUID idCoinBase) {
        return findByIdCoinPort.execute(idCoinBase)
                .orElseThrow(CoinNotFoundException::new);
    }

    private void setProductFields(UUID idProduct, TransactionEntity entity) {
        ProductEntity productEntity = findByIdProductPort.execute(idProduct)
                .orElseThrow(ProductNotFoundException::new);

        entity.setProduct(productEntity);
        entity.setVlUnit(productEntity.getVlUnit());
        entity.setCoinRequested(productEntity.getCoinBase());
    }

    private void setValueFields(UUID idCoinUsed, UUID idCoinRequested, TransactionEntity entity) {
        BigDecimal totalValue = entity.getVlUnit().multiply(BigDecimal.valueOf(entity.getQuantity()));

        if (idCoinUsed.equals(idCoinRequested)) {
            entity.setVlRate(BigDecimal.ONE);
            entity.setVlConverted(totalValue);
        } else {
            setExchangeRateFields(idCoinUsed, idCoinRequested, entity, totalValue);
        }
    }

    private void setExchangeRateFields(UUID idCoinUsed, UUID idCoinRequested, TransactionEntity entity, BigDecimal totalValue) {
        ExchangeRateEntity exchangeRate = findByCoinsExchangeRatePort.execute(idCoinUsed, idCoinRequested)
                .orElseThrow(ExchangeRateNotFoundException::new);

        entity.setVlRate(exchangeRate.getVlRate());
        if (idCoinUsed.equals(exchangeRate.getCoinFrom().getId())) {
            entity.setVlConverted(totalValue.divide(exchangeRate.getVlRate(), 2, RoundingMode.HALF_UP));
        } else {
            entity.setVlConverted(totalValue.multiply(exchangeRate.getVlRate()));
        }
    }

}