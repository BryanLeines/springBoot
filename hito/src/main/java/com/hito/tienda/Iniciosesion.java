package com.hito.tienda;

import com.hito.modelo.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class Iniciosesion {
    private Usuario inicio;

    public Iniciosesion(){

        this.inicio=null;
    }
    public Usuario getInicio(){

        return inicio;
    }

    public void setInicio(Usuario inicio){

        this.inicio=inicio;
    }
}
