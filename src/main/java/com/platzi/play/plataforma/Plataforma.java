package com.platzi.play.plataforma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.platzi.play.contenido.Genero;
import com.platzi.play.contenido.Pelicula;
import com.platzi.play.contenido.ResumenContenido;
import com.platzi.play.excepcion.PeliculaExistenteException;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;
    private Map<Pelicula, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Pelicula elemento) {
        Pelicula pelicula = this.buscarPorTitulo(elemento.getTitulo());
        if (pelicula != null) {
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }
        this.contenido.add(elemento);
    }

    public void reproducir(Pelicula pelicula) {
        int conteoActual = visualizaciones.getOrDefault(pelicula, 0);
        System.out.println("El contenido %s se ha reproducito %d veces".formatted(
            pelicula.getTitulo(),
            conteoActual
        ));
        this.contarVisualizacion(pelicula);
        pelicula.reproducir();
    }

    private void contarVisualizacion(Pelicula pelicula){
        visualizaciones.put(
            pelicula,
            visualizaciones.getOrDefault(pelicula, 0) + 1
        );
    };

    public void eliminar(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarPorTitulo(String titulo) {
        return contenido.stream()
            .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
            .filter(pelicula -> pelicula.getGenero().equals(genero))
            .toList();
    }

    public List<String> getTitulos() {
        return contenido.stream()
            .map(Pelicula::getTitulo)
            .toList();
    }

    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
            .map(pelicula -> new ResumenContenido(
                pelicula.getTitulo(),
                pelicula.getDuracion(),
                pelicula.getGenero()
            ))
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
