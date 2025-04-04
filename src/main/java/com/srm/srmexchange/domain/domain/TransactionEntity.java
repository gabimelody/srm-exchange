package com.srm.srmexchange.domain.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "VL_UNIT", nullable = false)
    private BigDecimal vlUnit;

    @Column(name = "VL_RATE", nullable = false)
    private BigDecimal vlRate;

    @Column(name = "VL_CONVERTED", nullable = false)
    private BigDecimal vlConverted;

    @Column(name = "DT_TRANSACTION", nullable = false)
    private LocalDateTime dtTransaction;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ID_COIN_USED", referencedColumnName = "ID", nullable = false)
    private CoinEntity coinUsed;

    @ManyToOne
    @JoinColumn(name = "ID_COIN_REQUESTED", referencedColumnName = "ID", nullable = false)
    private CoinEntity coinRequested;

}