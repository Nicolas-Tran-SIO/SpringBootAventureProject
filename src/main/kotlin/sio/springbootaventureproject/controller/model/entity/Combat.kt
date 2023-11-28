package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class Combat constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
){

    @ManyToOne
    @JoinColumn(name = "campagne_id")
    var campagne: Campagne? = null

    @ManyToOne
    @JoinColumn(name = "personnage")
    var personnage: Personnage? = null

}