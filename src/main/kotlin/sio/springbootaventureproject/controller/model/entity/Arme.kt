package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany


@Entity
    @DiscriminatorValue("arme")
    class Arme constructor(
        id: Long,
        nom: String,
        description: String,
        cheminImage: String?,
//TODO Attributs spécifiques aux armes
        //Association entre Arme et Qualite
        //Plusieurs armes peuvent être rataché a une qualite
        @ManyToOne
        @JoinColumn(name = "qualite_id")
        var qualite: Qualite? = null,

        //TODO Faire l'association avec TypeArme
        @ManyToOne
        @JoinColumn(name="typeArme_id")
        var armetype: TypeArme?= null,

        @OneToMany(mappedBy ="arme")
        var personnages: List<Personnage>? = null
    ) : Item(id, nom, description, cheminImage) {
    fun calculerDegats(): Int {
        // Exemple : 1d6 +2 ( cad un nombre entre 1 et 6 plus le modificateur 2)
        //      On tire 1 dè de 6 (min: 1 max:6 )
        //      Si on tire 6 alors on multiplie par le multiplicateur de critique du type de l'arme ( par exemple 3)
        //      Sinon on garde le nombre du tirage tel quelle
        //      On ajoute le bonus de qualite au dégat ici 2

        // Instanciation d'un tirage de dés
        val deDegat = TirageDeDes(this.armetype!!.nombreDes, armetype!!.valeurDeMax)
        // on lance les dés
        var resultatLancer = deDegat.lance()

        val deCritique = TirageDeDes(1, 20).lance()
        if (deCritique >= armetype!!.activationCritique) {
            // Coup critique (si le nombre tiré correspond au maximum)
            println("Coup critique !")
            resultatLancer = resultatLancer * armetype!!.multiplicateurCritique
        }
        resultatLancer += this.qualite!!.bonusQualite
        return resultatLancer
    }
    /**
     * Équipe l'arme sur un personnage, permettant au personnage de l'utiliser pour attaquer.
     *
     * @param cible Le personnage sur lequel l'arme est équipée.
     */
    override fun utiliser(cible: Personnage):String {
        return cible.equipe(this)
    }

}
