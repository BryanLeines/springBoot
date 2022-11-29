package com.hito;

import com.hito.modelo.Usuario;
import com.hito.tienda.Iniciosesion;
import com.hito.tienda.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class Controlador {
    private Usuarios usuarios;
    private Iniciosesion inicio;
    
    public Controlador(Usuarios usuarios){
        this.usuarios=usuarios;
    }
    @Autowired
    public void setInicio(Iniciosesion inicio) {
        this.inicio = inicio;
    }
    @RequestMapping("/")
    public ModelAndView peticion1(HttpServletRequest request){
        HttpSession sesion = request.getSession();
        return getModelAndView(request, sesion);
    }
    @RequestMapping("/reseñas")
    public String peticion2(ModelMap model){
       model.addAttribute("sms1","Nuestro lectores recomiendan algunos libros/comics/mangas");
       model.addAttribute("sms2","A continuación, podras leerlo");
        Set<Usuario> coleccionUsuarios=new HashSet<>(this.usuarios.getUsuarios().values());
        model.addAttribute("usuarios",coleccionUsuarios);
        return ("reseñas");
    }
    @RequestMapping("/leer")
    public String peticion3(@ModelAttribute("mensaje") String[] mensaje, ModelMap model){
        if (inicio.getInicio()==null) {
            return "nosesion";
        }
        else {

            return "leer";
        }
    }
   @ModelAttribute("mensaje")
   public String[] getMensaje(){
        String[] textos = new String[3];
        textos[0]="Gestor de recomendaciones";
        if (inicio.getInicio()==null)
            textos[1]="Sin inicio de sesión";
        else
            textos[1]="Usuario: " + inicio.getInicio().getUser();
        textos[2]="Añadir una nueva recomendacion";
        return textos;
   }
   @RequestMapping("/login")
   public String peticion4(Usuarios user, ModelMap model){
        model.addAttribute("login",new Usuario());
        return "login";
   }
    @RequestMapping("/comprobar")
    public String peticion5(Usuario user, ModelMap model) {
        System.out.println(user.getUser());
        System.out.println(user.getPassword());
        Usuario inicio = this.usuarios.getUsuarios().get(user.getUser());
        if (inicio==null) {
            model.addAttribute("sms", "Usuario no registrado, no se ha podido iniciar sesión");
        }
        else {
            if (user.getPassword().equals(inicio.getPassword())) {
                model.addAttribute("sms", "Bienvenido "+user.getUser());
                this.inicio.setInicio(inicio);
            }
            else {
                model.addAttribute("sms", "El password no es correcto");
            }
        }
        return "comprobar";
    }
    @RequestMapping("/cerrar")
    public ModelAndView peticion6(HttpServletRequest request){
     HttpSession sesion = request.getSession();
     sesion.invalidate();
     return getModelAndView(request,sesion);
    }
    private ModelAndView getModelAndView(HttpServletRequest request, HttpSession sesion) {
        ModelAndView mv = new ModelAndView();

        if (inicio.getInicio()==null){
            mv.addObject("sms3","No has iniciado sesión");
        }
        else{
            mv.addObject("sms3","Sesion iniciada: " + inicio.getInicio().getUser());
        }

        mv.addObject("inicio",inicio.getInicio());
        Set<Usuario>coleccionUsuarios= new HashSet<Usuario>(this.usuarios.getUsuarios().values());
        mv.addObject("usuarios",coleccionUsuarios);
        mv.setViewName("index");
        return mv;

    }
}
