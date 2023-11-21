package sio.springbootaventureproject.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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

        // Ajoute la qualité au modèle pour affichage dans la vue
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
}
