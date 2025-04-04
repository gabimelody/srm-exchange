package com.srm.srmexchange.application.mapper;

import com.srm.srmexchange.application.dto.transaction.TransactionInbound;
import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import com.srm.srmexchange.domain.domain.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CoinEntityMapper.class,
        ProductEntityMapper.class})
public interface TransactionEntityMapper {

    TransactionEntity toEntity(TransactionInbound inbound);
    TransactionOutbound toOutbound(TransactionEntity entity);

}