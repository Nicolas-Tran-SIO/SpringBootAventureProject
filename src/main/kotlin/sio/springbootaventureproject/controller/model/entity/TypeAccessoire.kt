package sio.springbootaventureproject.controller.model.entity
import jakarta.persistence.*

@Entity
class TypeAccessoire (
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom: String,
    var typeBonus: String,

    @OneToMany(mappedBy = "accessoiretype")
    var accessoires: List<Accessoire>?= mutableListOf()
) {
}