package sio.springbootaventureproject.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import sio.springbootaventureproject.controller.model.dao.TypeArmureDao
import sio.springbootaventureproject.controller.model.entity.TypeArme
import sio.springbootaventureproject.controller.model.entity.TypeArmure

@Controller
class TypeArmureController (val typeArmureDao: TypeArmureDao) {
    /**
     * Affiche la liste de tout les types armures.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/typeArmure")
    fun index(model: Model): String {
        // Récupère toutes les qualités depuis la base de données
        val listTypeArmure = this.typeArmureDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("listTypeArmure", listTypeArmure)

        // Retourne le nom de la vue à afficher
        return "admin/typearmure/index"
    }

    @GetMapping("/admin/typeArmure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val unTypeArmure = this.typeArmureDao.findById(id).orElseThrow()

        // Ajoute le type armure au modèle pour affichage dans la vue
        model.addAttribute("LetypeArmure", unTypeArmure)

        // Retourne le nom de la vue à afficher
        return "admin/typearmure/show"
    }

    @GetMapping("/admin/typeArmure/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Qualite avec des valeurs par défaut
        val nouveauTypeArmure = TypeArmure(null, "", 0)

        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveauTypeArmure", nouveauTypeArmure)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/typearmure/create"
    }
    @PostMapping("/admin/typeArmure")
    fun store(@ModelAttribute nouveauTypeArmure:TypeArmure,redirectAttributes: RedirectAttributes): String {
        // Sauvegarde le nouveau type d'armure dans la base de données
        val savedQualite = this.typeArmureDao.save(nouveauTypeArmure)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedQualite.nom} réussi")
        // Redirige vers la page d'administration des types d'armure
        return "redirect:/admin/typeArmure"
    }
    @GetMapping("/admin/typeArmure/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val unTypeArmureEdit = this.typeArmureDao.findById(id).orElseThrow()

        // Ajoute le type armure au modèle pour affichage dans la vue
        model.addAttribute("LeTypeArmureEdit", unTypeArmureEdit)

        // Retourne le nom de la vue à afficher
        return "admin/typearmure/edit"
    }
    @PostMapping("/admin/typeArmure/update")
    fun update(@ModelAttribute LeTypeArmureEdit: TypeArmure, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité existante dans la base de données
        val typeArmureModifier = this.typeArmureDao.findById(LeTypeArmureEdit.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de la qualité avec les nouvelles valeurs du formulaire
        typeArmureModifier.nom = LeTypeArmureEdit.nom
        typeArmureModifier.bonusType = LeTypeArmureEdit.bonusType

        // Sauvegarde la qualité modifiée dans la base de données
        val savedQualite = this.typeArmureDao.save(typeArmureModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedQualite.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/typeArmure"
    }
    @PostMapping("/admin/typeArmure/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val typeArmure: TypeArmure = this.typeArmureDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.typeArmureDao.delete(typeArmure)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeArmure.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/typeArmure"
    }
}