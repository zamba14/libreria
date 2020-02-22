
package com.libreria.libreria.servicios;

import com.libreria.libreria.Errores.ErrorServicio;
import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.entidades.Editorial;
import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.repositorios.AutorRepositorio;
import com.libreria.libreria.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.libreria.repositorios.LibroRepositorio;

@Service
public class LibroServicio {
    @Autowired
    LibroRepositorio libroRepositorio;
    @Autowired
    AutorRepositorio autorRepositorio;
    @Autowired
    EditorialRepositorio editorialRepositorio;
    public void registrar(String titulo, int anio, int ejemplares, Autor autor, Editorial editorial) throws ErrorServicio{
        if (titulo.isEmpty() || autor == null || editorial == null){
            throw new ErrorServicio("alguno de los atributos es nulo");
        }
        
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
        System.out.println("Libro guardado");
        System.out.println(libro.getTitulo());
        System.out.println(libro.getAutor().getNombre());
        System.out.println(libro.getEditorial().getNombre());
    }
    
    public void registrar(String titulo, int anio, int ejemplares, String autor, String editorial) throws ErrorServicio{
        if (titulo.isEmpty() || autor == null || editorial == null){
            throw new ErrorServicio("alguno de los atributos es nulo");
        }
        System.out.println(autor);
        System.out.println(editorial);
        Autor thisautor = autorRepositorio.getOne(autor);
        Editorial thiseditorial = editorialRepositorio.getOne(editorial);
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setAutor(thisautor);
        libro.setEditorial(thiseditorial);
        libroRepositorio.save(libro);
        System.out.println("Libro guardado");
        System.out.println(libro.getTitulo());
        System.out.println(libro.getAutor().getNombre());
        System.out.println(libro.getEditorial().getNombre());
    }
    
    public Libro validarLibro(String libroId) throws ErrorServicio{
        Libro libro = libroRepositorio.getOne(libroId);
        int cantidad = libro.getPrestados();
        if (cantidad<libro.getEjemplares()){
            libro.setPrestados(cantidad+1);
            System.out.println(libro.getPrestados());
            
            libroRepositorio.save(libro);
            return libro;
        } else {
            throw new ErrorServicio("No hay ejemplares para prestar");
           
        }
        
    }
}
