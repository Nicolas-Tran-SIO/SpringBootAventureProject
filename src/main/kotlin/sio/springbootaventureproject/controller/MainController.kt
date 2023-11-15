package sio.springbootaventureproject.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/home")
    fun index():String {
        return "visiteur/home"
    }

    @GetMapping("/login")
    fun login():String{
        return "visiteur/login"
    }

    @GetMapping("/inscription")
    fun inscription():String{
        return "visiteur/inscription"
    }
}