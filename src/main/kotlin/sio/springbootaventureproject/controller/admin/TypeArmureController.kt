package sio.springbootaventureproject.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import sio.springbootaventureproject.controller.model.dao.TypeArmureDao
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
}