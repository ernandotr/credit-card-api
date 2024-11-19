package dev.eernandorezende.credit_card_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "tb_holder")
public class Holder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String vatNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
