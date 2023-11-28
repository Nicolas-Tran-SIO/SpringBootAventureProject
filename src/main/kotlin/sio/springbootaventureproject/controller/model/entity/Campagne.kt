package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class Campagne constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var score: Int?,
    var etat: String,
    var dernierScore: Int?,
    var meilleurScore: Int?
){

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    var utilisateur: Utilisateur? = null

    @ManyToOne
    @JoinColumn(name = "personnage")
    var personnage: Personnage? = null

    @OneToMany(mappedBy = "campagne")
    var combats: List<Combat>? = null

}