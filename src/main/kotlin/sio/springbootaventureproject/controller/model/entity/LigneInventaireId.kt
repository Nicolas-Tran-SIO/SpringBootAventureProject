package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class LigneInventaireId(
    val personnageId: Long,
    val itemId: Long
):Serializable
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LigneInventaireId

        if (personnageId != other.personnageId) return false
        if (itemId != other.itemId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = personnageId.hashCode()
        result = 31 * result + itemId.hashCode()
        return result
    }
}