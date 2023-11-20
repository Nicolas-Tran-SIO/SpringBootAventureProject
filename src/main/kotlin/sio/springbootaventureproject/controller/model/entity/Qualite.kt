package sio.springbootaventureproject.controller.model.entity
import jakarta.persistence.*
@Entity
class Qualite constructor(
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom: String,
    var couleur: String,
    var bonusQualite: Int
    //Todo Mission 3 ajoutez les associations
    ){

}