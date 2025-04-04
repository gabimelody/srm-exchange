package com.srm.srmexchange.domain.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXCHANGE_RATE")
public class ExchangeRateEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "VL_RATE", nullable = false)
    private BigDecimal vlRate;

    @ManyToOne
    @JoinColumn(name = "ID_COIN_TO", referencedColumnName = "ID", nullable = false)
    private CoinEntity coinTo;

    @ManyToOne
    @JoinColumn(name = "ID_COIN_FROM", referencedColumnName = "ID", nullable = false)
    private CoinEntity coinFrom;

}