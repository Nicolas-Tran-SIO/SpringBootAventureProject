package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class TypeArme constructor(
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom:String,
    var nombreDes: Int,
    var valeurDeMax: Int,
    var multiplicateurCritique:Int,
    var activationCritique:Int,

    @OneToMany(mappedBy = "armetype")
    var armes: List<Arme>?= mutableListOf()
) {
}