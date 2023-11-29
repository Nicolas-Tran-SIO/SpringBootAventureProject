package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class LigneInventaire (
    @EmbeddedId
    var ligneInventaireId: LigneInventaireId?=null,

    @MapsId("personnageId")
    @ManyToOne
    @JoinColumn(name= "personnage_id")
    var personnage: Personnage? = null,

    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name= "item_id")
    var item: Item? = null,
)
{

}