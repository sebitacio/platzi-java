package com.platzi.play.plataforma;

import java.util.ArrayList;
import java.util.List;

import com.platzi.play.content.Pelicula;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);
    }

    public void eliminar(Pelicula pelicula){
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo){
        for (Pelicula pelicula : contenido) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){
                return pelicula;
            }
        }

        return null;
    }

    public void mostrarTitulos(){
     for (Pelicula pelicula : contenido) {
        System.out.println(pelicula.getTitulo());
     }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
