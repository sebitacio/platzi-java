package com.platzi.play.plataforma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.platzi.play.content.Pelicula;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);
    }

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        return contenido.stream()
            .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(String genero) {
        return contenido.stream()
            .filter(pelicula -> pelicula.getGenero().equalsIgnoreCase(genero))
            .toList();
    }

    public List<String> getTitulos() {
        return contenido.stream()
            .map(Pelicula::getTitulo)
            .toList();
    }

    public int getDuracionTotal(){
        return contenido.stream()
            .mapToInt(Pelicula::getDuracion)
            .sum();
    }

    public List<Pelicula> getPopulares(){
        return contenido.stream()
            .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
            .filter(pelicula -> pelicula.getCalificacion() >= 4)
            .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
