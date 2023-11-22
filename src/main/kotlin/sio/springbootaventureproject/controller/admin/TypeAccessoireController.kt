package sio.springbootaventureproject.controller.admControllerContr

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import sio.springbootaventureproject.controller.model.dao.TypeAccessoireDao
import sio.springbootaventureproject.controller.model.entity.TypeAccessoire

@Controller
class TypeAccessoireController(val TypeAccessoireDao: TypeAccessoireDao){

    @GetMapping("/admin/typeAccessoire")
    fun index(model: Model): String{
        val listTypeAccessoire = this.TypeAccessoireDao.findAll()
        model.addAttribute("listTypeAccessoire", listTypeAccessoire)

        return "admin/typeaccessoire/index"
    }
    @GetMapping("/admin/typeAccessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val unTypeAccessoire = this.TypeAccessoireDao.findById(id).orElseThrow()
        model.addAttribute("unTypeAccessoire", unTypeAccessoire)

        return "admin/typeaccessoire/show"
    }

    @GetMapping("/admin/typeAccessoire/create")
    fun create(model: Model): String {
        val nouveauTypeAccessoire = TypeAccessoire(null, "", "")
        model.addAttribute("nouveauTypeAccessoire", nouveauTypeAccessoire)

        return "admin/typeaccessoire/create"
    }

    @PostMapping("/admin/typeAccessoire")
    fun store(@ModelAttribute nouveauAccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        val savedAccessoire = this.TypeAccessoireDao.save(nouveauAccessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedAccessoire.nom} réussi")

        return "redirect:/admin/typeAccessoire"
    }

    @GetMapping("/admin/typeAccessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val unTypeAccessoireEdit = this.TypeAccessoireDao.findById(id).orElseThrow()
        model.addAttribute("LeTypeAccessoireEdit", unTypeAccessoireEdit)

        return "admin/typeaccessoire/edit"
    }

    @PostMapping("/admin/typeAccessoire/update")
    fun update(@ModelAttribute accessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        val typeAccessoireModifier = this.TypeAccessoireDao.findById(accessoire.id ?: 0).orElseThrow()

        typeAccessoireModifier.nom = accessoire.nom
        typeAccessoireModifier.typeBonus = accessoire.typeBonus

        val savedAccessoire = this.TypeAccessoireDao.save(typeAccessoireModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedAccessoire.nom} réussie")

        return "redirect:/admin/typeAccessoire"
    }

    @PostMapping("/admin/typeAccessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val typeAccessoire: TypeAccessoire = this.TypeAccessoireDao.findById(id).orElseThrow()
        this.TypeAccessoireDao.delete(typeAccessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeAccessoire.nom} réussie")

        return "redirect:/admin/typeAccessoire"
    }

}