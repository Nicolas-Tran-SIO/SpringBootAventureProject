package sio.springbootaventureproject.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import sio.springbootaventureproject.controller.model.dao.QualiteDao
import sio.springbootaventureproject.controller.model.entity.Qualite
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class QualiteController(val qualiteDao: QualiteDao) {
    /**
     * Affiche la liste de toutes les qualités.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/qualite")
    fun index(model: Model): String {
        // Récupère toutes les qualités depuis la base de données
        val qualites = this.qualiteDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("qualites", qualites)

        // Retourne le nom de la vue à afficher
        return "admin/qualite/index"
    }
    /**
     * Affiche les détails d'une qualité en particulier.
     *
     * @param id L'identifiant unique de la qualité à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/qualite/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneQualite = this.qualiteDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("qualite", uneQualite)

        // Retourne le nom de la vue à afficher
        return "admin/qualite/show"
    }
    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/qualite/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Qualite avec des valeurs par défaut
        val nouvelleQualite = Qualite(null, "", "", 0)

        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleQualite", nouvelleQualite)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/qualite/create"
    }
    /**
     * Gère la soumission du formulaire d'ajout de qualité.
     *
     * @param nouvelleQualite L'objet Qualite créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après l'ajout réussi.
     */
    @PostMapping("/admin/qualite")
    fun store(@ModelAttribute nouvelleQualite: Qualite, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle qualité dans la base de données
        val savedQualite = this.qualiteDao.save(nouvelleQualite)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedQualite.nom} réussi")
        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/qualite"
    }
    @GetMapping("/admin/qualite/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneQualite = this.qualiteDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("qualite", uneQualite)

        // Retourne le nom de la vue à afficher
        return "admin/qualite/edit"
    }
    /**
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant de la qualité à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la suppression réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/qualite/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val qualite: Qualite = this.qualiteDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.qualiteDao.delete(qualite)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${qualite.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/qualite"
    }
}