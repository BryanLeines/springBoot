package com.hito.tienda;

import com.hito.modelo.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.TreeMap;

@Service
@ApplicationScope
public class Usuarios {
    private TreeMap<String, Usuario>usuarios;

    public Usuarios(){
        this.usuarios =new TreeMap<String, Usuario>();
        this.usuarios.put("Maria",new Usuario("Maria","1234"));
        this.usuarios.get("Maria").getRecomendaciones().add("Dragon Ball");
        this.usuarios.get("Maria").getRecomendaciones().add("One Piece");
        this.usuarios.get("Maria").getRecomendaciones().add("Naruto");

        this.usuarios.put("Antonio",new Usuario("Antonio","2468"));
        this.usuarios.get("Antonio").getRecomendaciones().add("Superman");
        this.usuarios.get("Antonio").getRecomendaciones().add("X-Men");
        this.usuarios.get("Antonio").getRecomendaciones().add("Batman");

        this.usuarios.put("Ana",new Usuario("Ana","3692"));
        this.usuarios.get("Ana").getRecomendaciones().add("Herido Diario");
        this.usuarios.get("Ana").getRecomendaciones().add("No Me Llores");
        this.usuarios.get("Ana").getRecomendaciones().add("La triste historia de tu cuerpo sobre el mio");
    }
    public TreeMap<String,Usuario> getUsuarios(){return usuarios;}
}
