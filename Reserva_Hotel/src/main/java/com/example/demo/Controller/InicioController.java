package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.Service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inicio")
public class InicioController {
    @Autowired
    @Qualifier("usuarioServicio")
    private UsuarioService usuarioService;
    // ("usuarioServicio") debe coincidir con el nombre del servicio implementado
    // permite usar los métodos del servicio UsuarioService

    @GetMapping("/login")
    public String inicio() {
        ModelAndView mav = new ModelAndView("Inicio");
        // ModelAndView("Inicio") => busca el archivo Inicio.html en la carpeta templates
        mav.addObject("correo", "");
        mav.addObject("password", "");
        return "Inicio"; // retorna la vista Inicio.html para el método GET
    }

    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo, @RequestParam("password") String password) {
        if (usuarioService.buscarExisteUsuario(correo,password)) {
            if(usuarioService.esAdmin(correo)){
                //System.out.println("El usuario es administrador: " + correo);
                return "redirect:/menu/inicio"; // Redirige al dashboard de administrador
            } else {
                //System.out.println("El usuario es cliente: " + correo);
                return "redirect:/cliente/home"; // Redirige a la página principal del cliente
            }
        } else {
            //System.out.println("Error de autenticación para el usuario: " + correo);
            return "redirect:/inicio/NO_INGRESO";
        }
        // return "redirect:/inicio/login"; => redirige a la URL /inicio/login
        // solo se usa en POST o en métodos que no retornan vistas
    }

}
