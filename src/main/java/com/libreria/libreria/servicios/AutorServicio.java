

package com.libreria.libreria.servicios;

import com.libreria.libreria.Errores.ErrorServicio;
import com.libreria.libreria.entidades.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.libreria.repositorios.AutorRepositorio;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorRepositorio;
    public void registrar(String nombre) throws ErrorServicio{
        if (!nombre.isEmpty()){
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepositorio.save(autor);
            System.out.println("Autor registrado");
        }else{
            throw new ErrorServicio("Nombre vacío");
        }
    }
}
