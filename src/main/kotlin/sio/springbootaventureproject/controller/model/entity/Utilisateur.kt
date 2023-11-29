package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class Utilisateur constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom: String,
    var email: String,
    var mdp: String
    ){

    @OneToMany(mappedBy = "utilisateur")
    var personnages: List<Personnage>? = null

    @OneToMany(mappedBy = "utilisateur")
    var campagnes: List<Campagne>? = null

    @ManyToMany
    @JoinTable(
        name = "utilisateur_role",
        joinColumns = [JoinColumn(name = "utilisateur_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: List<Role>? = null

}