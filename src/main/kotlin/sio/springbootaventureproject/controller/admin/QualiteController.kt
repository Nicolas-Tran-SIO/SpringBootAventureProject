package sio.springbootaventureproject.controller.admin

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
@RestController
class QualiteController {
    @GetMapping("/")
    fun index():String {
        return "accueil"
    }
}