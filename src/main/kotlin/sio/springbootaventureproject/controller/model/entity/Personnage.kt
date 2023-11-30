package sio.springbootaventureproject.controller.model.entity

import jakarta.persistence.*

@Entity
class Personnage constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    var id:Long? = null,
    var nom:String,
    var attaque:Int,
    var defense:Int,
    var endurance:Int,
    var vitesse:Int,
    var cheminImage:String,

    @ManyToOne
    @JoinColumn(name="arme_id")
    var arme: Arme?=null,

    @ManyToOne
    @JoinColumn(name="armure_id")
    var armure: Armure?=null,

    @ManyToOne
    @JoinColumn(name="accessoire_id")
    var accessoire: Accessoire?=null,

    @OneToMany(mappedBy = "personnage")
    val ligneItem: MutableList<LigneInventaire> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "utilisateurId")
    var utilisateur: Utilisateur
    ) {
    val pointDeVieMax: Int
        get() = 50 + (10 * (this.endurance))

    var pointDeVie: Int = this.pointDeVieMax
        set(value) {
            field = minOf(value, this.pointDeVieMax)

        }
    open fun attaquer(adversaire: Personnage):String {
        // Vérifier si le personnage a une arme équipée
        var degats = this.attaque / 2
        if (arme != null) {
            // Calculer les dégâts en utilisant les attributs du personnage et la méthode calculerDegat de l'arme
            degats += this.arme!!.calculerDegats()
        }
        // Appliquer la défense de l'adversaire (au minimum au moins 1 de dégat)
        val degatsInfliges = maxOf(1, degats - adversaire.calculeDefense())


        // Appliquer les dégâts à l'adversaire
        adversaire.pointDeVie = adversaire.pointDeVie - degatsInfliges

        return("$nom attaque ${adversaire.nom} avec ${arme?.nom ?: "une attaque de base"} et inflige $degatsInfliges points de dégâts.")
    }

    fun calculeDefense(): Int {
        var resultat = this.defense / 2
        val scoreArmure =
            (this.armure?.armuretype?.bonusType ?: 0) + (this.armure?.qualite?.bonusQualite ?: 0)
        resultat += scoreArmure
        return resultat;

    }

    /**
     * Ajoute une ligne d'inventaire pour l'item spécifié avec la quantité donnée.
     * Si une ligne d'inventaire pour cet item existe déjà, met à jour la quantité.
     * Si la quantité résultante est inférieure ou égale à zéro, la ligne d'inventaire est supprimée.
     *
     * @param unItem L'item pour lequel ajouter ou mettre à jour la ligne d'inventaire.
     * @param uneQuantite La quantité à ajouter à la ligne d'inventaire existante ou à la nouvelle ligne.
     */
    fun ajouterLigneInventaire(unItem: Item, uneQuantite: Int) {
        // Chercher une ligne d'inventaire existante pour l'item spécifié
        val ligneItem = this.ligneItem.find { ligneInventaire -> ligneInventaire.item == unItem }

        // Si aucune ligne d'inventaire n'est trouvée, en créer une nouvelle
        if (ligneItem == null) {
            // Créer un nouvel identifiant pour la ligne d'inventaire
            val ligneInventaireId = LigneInventaireId(this.id!!, unItem.id!!)

            // Ajouter une nouvelle ligne d'inventaire à la liste
            this.ligneItem.add(LigneInventaire(ligneInventaireId, this,unItem,uneQuantite))
        } else {
            // Si une ligne d'inventaire existante est trouvée, mettre à jour la quantité
            ligneItem.quantite += uneQuantite

            // Si la quantité résultante est inférieure ou égale à zéro, supprimer la ligne d'inventaire
            if (ligneItem.quantite <= 0) {
                this.ligneItem.remove(ligneItem)
            }
        }
    }
    /**
     * Loot l'inventaire de la cible en transférant les items et équipements dans l'inventaire du looteur.
     *
     * @param cible Le personnage dont l'inventaire sera looted.
     * @return Un message décrivant les items looted et les actions effectuées.
     */
    fun loot(cible: Personnage): String {
        // Déséquiper l'arme et l'armure de la cible
        cible.arme = null
        cible.armure = null
        // Variable pour stocker les messages générés pendant le loot
        var msg = ""
        // Parcourir chaque ligne d'inventaire de la cible
        for (uneLigne: LigneInventaire in cible.ligneItem) {
            // Ajouter les items et quantités lootés à l'inventaire du looteur
            this.ajouterLigneInventaire(uneLigne.item!!, uneLigne.quantite)

            // Construire un message décrivant l'action pour chaque item looté
            msg += "${this.nom} récupère ${uneLigne.quantite} ${uneLigne.item} <br>"
        }
        // Retourner le message global décrivant l'action de loot
        return msg
    }
    /**
     * Vérification si le personnage a une potion dans son inventaire.
     *
     * @return true si le personnage a une potion, false sinon.
     */
    fun aUnePotion(): Boolean {
        // Utiliser la méthode any pour vérifier si une ligne d'inventaire contient une Potion
        return this.ligneItem.any { ligneInventaire -> ligneInventaire.item is Potion }
    }

    fun equipe(uneArme: Arme): String{
        var msg:String
        // Utiliser la méthode any pour vérifier si une ligne d'inventaire contient une Arme
        if(this.ligneItem.any { ligneInventaire -> ligneInventaire.item is Arme }){
            this.arme=uneArme
            msg="${uneArme.nom} est equiper par ${this.nom}<br>"
        }
        else{
            msg="Action impossible <br>"
        }
        return msg
    }
    fun equipe(uneArmure: Armure): String{
        var msg:String
        // Utiliser la méthode any pour vérifier si une ligne d'inventaire contient une Arme
        if(this.ligneItem.any { ligneInventaire -> ligneInventaire.item is Armure }){
            this.armure=uneArmure
            msg="${uneArmure.nom} est equiper par ${this.nom}<br>"
        }
        else{
            msg="Action impossible <br>"
        }
        return msg
    }
    fun equipe(unAccessoire: Accessoire): String{
        var msg:String
        // Utiliser la méthode any pour vérifier si une ligne d'inventaire contient une Arme
        if(this.ligneItem.any { ligneInventaire -> ligneInventaire.item is Accessoire }){
            this.accessoire=unAccessoire
            msg="${unAccessoire.nom} est equiper par ${this.nom}<br>"
        }
        else{
            msg="Action impossible <br>"
        }
        return msg
    }

    /**
     * Méthode pour boire une potion de l'inventaire du personnage.
     *
     * @param consomer Spécifie si la potion doit être consommée (décrémentant la quantité) ou non.
     *                 Par défaut, la potion est consommée.
     * @return Un message décrivant l'action effectuée, tel que boire la potion ou l'absence de potion.
     */
    fun boirePotion(consomer: Boolean = true): String {
        // Message par défaut si le personnage n'a pas de potion dans son inventaire
        var msg = "$nom n'a pas de potion dans son inventaire."

        // Vérifier si le personnage a une potion dans son inventaire
        if (this.aUnePotion()) {
            // Filtrer les lignes d'inventaire pour obtenir celles qui contiennent des potions
            val lignePotions: List<LigneInventaire> =
                this.ligneItem.filter { ligneInventaire -> ligneInventaire.item is Potion }

            // Utiliser la première potion dans la liste et obtenir le message résultant de l'utilisation
            msg = lignePotions[0].item!!.utiliser(this)

            // Si consomer est false, augmenter la quantité de potions dans l'inventaire
            if (!consomer) {
                lignePotions[0].quantite += 1
            }
        }

        // Retourner le message décrivant l'action effectuée
        return msg
    }

}