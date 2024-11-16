package dev.eernandorezende.credit_card_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String holder;
    private String validThru;
    private Short cvc;
    private String number;
    private String lastDigits;
    private BigDecimal totalLimit;
    private BigDecimal usedLimit;
    private String cardName;
    private Byte invoiceDueDay;

    @ManyToOne
    @JoinColumn(name = "flag_id")
    private Flag flag;
}
