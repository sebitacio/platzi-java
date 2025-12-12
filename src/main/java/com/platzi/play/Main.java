package com.platzi.play;

import java.util.List;

import com.platzi.play.content.Pelicula;
import com.platzi.play.plataforma.Plataforma;
import com.platzi.play.plataforma.Usuario;
import com.platzi.play.util.ScannerUtils;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "Movie 🍿";
    public static final String VERSION = "v1.0.0";
    
    public static final int AGREGAR = 1;
    public static final int MOSTRAR = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " " + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Con " + plataforma.getDuracionTotal() + " minutos de contenido");

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                Ingrese una de las siguientes opciones:
                1. Agregar contenido
                2. Mostrar todo
                3. Buscar por titulo
                4. Buscar por genero
                5. Ver Populares
                8. Eliminar
                9.salit
                """);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String titulo = ScannerUtils.capturarTexto("Titulo del contenido");
                    String genero = ScannerUtils.capturarTexto("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del Contenido");

                    plataforma.agregar(new Pelicula(titulo, duracion, genero, calificacion));
                }
                case MOSTRAR -> {
                    plataforma.getTitulos().forEach(System.out::println);
                }
                case BUSCAR_POR_TITULO -> {
                    String titulo = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
                    Pelicula pelicula = plataforma.buscarPorTitulo(titulo);

                    if (pelicula != null){
                        System.out.println(pelicula.obtenerFichaTecnica());
                    }else{
                        System.out.println(titulo + " No existe dentro de la plataforma");
                    }

                }
                case BUSCAR_POR_GENERO -> {
                    String genero = ScannerUtils.capturarTexto("Nombre del genero a buscar");
                    List<Pelicula> peliculas = plataforma.buscarPorGenero(genero);

                    peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica()));
                }
                case VER_POPULARES -> {
                    List<Pelicula> populares = plataforma.getPopulares();
                    populares.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica()));
                }
                case ELIMINAR -> {
                    String titulo = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Pelicula pelicula = plataforma.buscarPorTitulo(titulo);

                    if (pelicula != null){
                        plataforma.eliminar(pelicula);
                        System.out.println(titulo + " ha sido eliminado de la plataforma");
                    }else{
                        System.out.println(titulo + " No existe dentro de la plataforma");
                    }
                }
                case SALIR -> System.exit(0);
            }
        }
    }

    private static void cargarPeliculas(Plataforma plataforma){
        plataforma.agregar(new Pelicula("Shrek", 90, "Animada"));
        plataforma.agregar(new Pelicula("Inception", 148, "Ciencia Ficcion"));
        plataforma.agregar(new Pelicula("Titanic", 195, "Drama", 4.6));
        plataforma.agregar(new Pelicula("John Wick", 101, "Accio"));
        plataforma.agregar(new Pelicula("El Conjuro", 112, "Terror", 3.0));
        plataforma.agregar(new Pelicula("Coco", 105, "Animada", 4.7));
        plataforma.agregar(new Pelicula("Interstellar", 169, "Ciencia Ficcion", 5));
        plataforma.agregar(new Pelicula("Joker", 122, "Drama"));
        plataforma.agregar(new Pelicula("Toy Story", 81, "Animada", 4.5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 181, "Accion", 3.9));
    }
}
