package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*


@Entity
@DiscriminatorValue("accessoire")
class Accessoire constructor(
    id: Long,
    nom: String,
    description: String,
    cheminImage: String?,
//TODO Attributs spécifiques aux accessoires
    //Association entre Accessoire et Qualite
    //Plusieurs accessoires peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,

    //TODO Faire l'association avec TypeAccessoire
    @ManyToOne
    @JoinColumn(name="typeAccessoire_id")
    var accessoiretype: TypeAccessoire?= null,

    @OneToMany(mappedBy ="accessoire")
    var personnages: List<Personnage>? = null

) : Item(id, nom, description, cheminImage) {


}