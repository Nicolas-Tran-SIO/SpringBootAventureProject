package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class TypeArme constructor(
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom:String,
    val nombreDes: Int,
    val valeurDeMax: Int,
    val multiplicateurCritique:Int,
    val activationCritique:Int
) {
}