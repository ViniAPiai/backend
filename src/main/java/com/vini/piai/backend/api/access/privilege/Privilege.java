package com.vini.piai.backend.api.access.privilege;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "privilege")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;




}