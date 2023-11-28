package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class Personnage constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    var id:Long? = null,
    var nom:String,
    var pointDeVie:Int,
    var pointDeVieMax:Int,
    var attaque:Int,
    var defense:Int,
    var endurance:Int,
    var vitesse:Int,
    var cheminImage:String,

    @ManyToOne
    @JoinColumn(name="arme_id")
    var arme: Arme?=null,

    @ManyToOne
    @JoinColumn(name="armure_id")
    var armure: Armure?=null,

    @ManyToOne
    @JoinColumn(name="accessoire_id")
    var accessoire: Accessoire?=null,

    @OneToMany(mappedBy = "personnage")
    val ligneItem: List<LigneInventaire> = mutableListOf()
    ){

}