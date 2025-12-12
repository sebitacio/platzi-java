package com.platzi.play.content;

import java.time.LocalDate;

public class Pelicula {
    private String titulo;
    private String description;
    private int duracion;
    private String genero;
    private LocalDate estreno;
    private double calificacion;
    private boolean disponible;

    public Pelicula(String titulo, int duracion, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.estreno = LocalDate.now();
        this.disponible = false;
    }

    public Pelicula(String titulo, int duracion, String genero, double calificacion) {
        this(titulo, duracion, genero);
        this.calificar(calificacion);
    } 



    public void reproducir() {
        System.out.println("Estoy reproduciendo " + titulo);
    }

    public String obtenerFichaTecnica() {
        return """
                ✨ %s (%d)
                Género: %s
                Calificación: %s
                """.formatted(
                titulo,
                estreno.getYear(),
                genero,
                calificacion);
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular() {
        return calificacion >= 4;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescription() {
        return description;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getEstreno() {
        return estreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    
}