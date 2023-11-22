package sio.springbootaventureproject.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import sio.springbootaventureproject.controller.model.dao.TypeArmeDao
import sio.springbootaventureproject.controller.model.entity.TypeArme

@Controller
class TypeArmeController(val typeArmeDao:TypeArmeDao) {
    /**
     * Affiche la liste de tout les types d'arme.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/typeArme")
    fun index(model: Model): String {
        // Récupère toutes les types d'arme depuis la base de données
        val listTypeArme = this.typeArmeDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("listTypeArme", listTypeArme)

        // Retourne le nom de la vue à afficher
        return "admin/typearme/index"
    }
    @GetMapping("/admin/typeArme/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val unTypeArme = this.typeArmeDao.findById(id).orElseThrow()

        // Ajoute le type arme au modèle pour affichage dans la vue
        model.addAttribute("LetypeArme", unTypeArme)

        // Retourne le nom de la vue à afficher
        return "admin/typearme/show"
    }
    @GetMapping("/admin/typeArme/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Qualite avec des valeurs par défaut
        val nouveauTypeArme = TypeArme(null, "", 0, 0,0,0)

        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveauTypeArme", nouveauTypeArme)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/typearme/create"
    }
    @PostMapping("/admin/typeArme")
    fun store(@ModelAttribute nouveauTypeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde le nouveau type d'arme dans la base de données
        val savedQualite = this.typeArmeDao.save(nouveauTypeArme)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedQualite.nom} réussi")
        // Redirige vers la page d'administration des types d'armes
        return "redirect:/admin/typeArme"
    }
    @GetMapping("/admin/typeArme/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val unTypeArmeEdit = this.typeArmeDao.findById(id).orElseThrow()

        // Ajoute le type arme au modèle pour affichage dans la vue
        model.addAttribute("LeTypeArmeEdit", unTypeArmeEdit)

        // Retourne le nom de la vue à afficher
        return "admin/typearme/edit"
    }
    @PostMapping("/admin/typeArme/update")
    fun update(@ModelAttribute LeTypeArmeEdit: TypeArme, redirectAttributes: RedirectAttributes): String {
        // Recherche de le type arme existante dans la base de données
        val typeArmeModifier = this.typeArmeDao.findById(LeTypeArmeEdit.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de la qualité avec les nouvelles valeurs du formulaire
        typeArmeModifier.nom = LeTypeArmeEdit.nom
        typeArmeModifier.nombreDes = LeTypeArmeEdit.nombreDes
        typeArmeModifier.valeurDeMax = LeTypeArmeEdit.valeurDeMax
        typeArmeModifier.multiplicateurCritique = LeTypeArmeEdit.multiplicateurCritique
        typeArmeModifier.activationCritique = LeTypeArmeEdit.activationCritique

        // Sauvegarde la qualité modifiée dans la base de données
        val savedQualite = this.typeArmeDao.save(typeArmeModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedQualite.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/typeArme"
    }
    @PostMapping("/admin/typeArme/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val typeArme: TypeArme = this.typeArmeDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.typeArmeDao.delete(typeArme)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeArme.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/typeArme"
    }

}
