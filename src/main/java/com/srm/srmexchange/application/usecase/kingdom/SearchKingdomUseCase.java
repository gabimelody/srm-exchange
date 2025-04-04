package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.in.kingdom.SearchKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindAllKingdomPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchKingdomUseCase implements SearchKingdomPort {

    private final FindAllKingdomPort findAllKingdomPort;
    private final KingdomEntityMapper kingdomEntityMapper;

    @Override
    public List<KingdomOutbound> execute() {
        log.info("Searching all kingdoms");
        return findAllKingdomPort.execute().stream()
                .map(this::mapToOutbound)
                .toList();
    }

    private KingdomOutbound mapToOutbound(KingdomEntity entity) {
        log.info("Kingdom found: {}", entity);
        return kingdomEntityMapper.toOutbound(entity);
    }

}