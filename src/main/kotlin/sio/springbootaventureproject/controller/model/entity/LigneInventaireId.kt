package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.Embeddable

@Embeddable
class LigneInventaireId{
    val personnageId: Long,
    val itemId: Long
}