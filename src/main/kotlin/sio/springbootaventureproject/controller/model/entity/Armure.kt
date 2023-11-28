package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*


@Entity
@DiscriminatorValue("armure")
class Armure constructor(
    id: Long,
    nom: String,
    description: String,
    cheminImage: String?,
//TODO Attributs spécifiques aux armures
    //Association entre Armure et Qualite
    //Plusieurs armures peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,

    //TODO Faire l'association avec TypeArmure
    @ManyToOne
    @JoinColumn(name="typeArmure_id")
    var armuretype: TypeArmure?= null,

    @OneToMany(mappedBy ="armure")
    var personnages: List<Personnage>? = null
) : Item(id, nom, description, cheminImage) {


}