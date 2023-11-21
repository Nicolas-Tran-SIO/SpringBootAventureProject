package sio.springbootaventureproject.controller.admControllerContr

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import sio.springbootaventureproject.controller.model.dao.AccessoireDao
import sio.springbootaventureproject.controller.model.entity.TypeAccessoire

@Controller
class AccessoireController(val accessoireDao: AccessoireDao){

    @GetMapping("/admin/accessoire")
    fun index(model: Model): String{
        val listAccessoire = this.accessoireDao.findAll()
        model.addAttribute("listAccessoire", listAccessoire)

        return "admin/accessoire/index"
    }
    @GetMapping("/admin/accessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val unAccessoire = this.accessoireDao.findById(id).orElseThrow()
        model.addAttribute("Accessoire", unAccessoire)

        return "admin/accessoire/show"
    }

    @GetMapping("/admin/accessoire/create")
    fun create(model: Model): String {
        val nouveauAccessoire = TypeAccessoire(null, "", "")
        model.addAttribute("nouveauAccessoire", nouveauAccessoire)

        return "admin/accessoire/create"
    }

    @PostMapping("/admin/accessoire")
    fun store(@ModelAttribute nouveauAccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        val savedAccessoire = this.accessoireDao.save(nouveauAccessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedAccessoire.nom} réussi")

        return "redirect:/admin/accessoire"
    }

    @GetMapping("/admin/accessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val unAccessoire = this.accessoireDao.findById(id).orElseThrow()
        model.addAttribute("Accessoire", unAccessoire)

        return "admin/accessoire/edit"
    }

    @PostMapping("/admin/accessoire/update")
    fun update(@ModelAttribute accessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        val accessoireModifier = this.accessoireDao.findById(accessoire.id ?: 0).orElseThrow()

        accessoireModifier.nom = accessoire.nom
        accessoireModifier.typeBonus = accessoire.typeBonus

        val savedAccessoire = this.accessoireDao.save(accessoireModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedAccessoire.nom} réussie")

        return "redirect:/admin/accessoire"
    }

    @PostMapping("/admin/accessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val accessoire: TypeAccessoire = this.accessoireDao.findById(id).orElseThrow()
        this.accessoireDao.delete(accessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${accessoire.nom} réussie")

        return "redirect:/admin/accessoire"
    }

}