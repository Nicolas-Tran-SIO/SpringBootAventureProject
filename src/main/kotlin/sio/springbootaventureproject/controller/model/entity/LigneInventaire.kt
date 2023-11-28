package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.EmbeddedId

class LigneInventaire {
    @EmbeddedId
    var ligneInventaireId: LigneInventaireId?=null,
}