package com.bhuvanesh.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    Long id;

    @Column(name = "`name`")
    String name;

    @Column(name = "`surname`")
    String surname;

    @Column(name = "`address`")
    String address;

    @Column(name = "`email`")
    String email;

    @Column(name = "`alerting`")
    Boolean userNotification;

    @Column(name = "`alerting_Threshold`")
    Double energyAlertingThreshold;
}
