package dev.eernandorezende.credit_card_api.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_flag")
public class Flag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
