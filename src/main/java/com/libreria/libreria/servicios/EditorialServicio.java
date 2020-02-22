
package com.libreria.libreria.servicios;

import com.libreria.libreria.Errores.ErrorServicio;
import com.libreria.libreria.entidades.Editorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.libreria.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio{
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    public void registrar(String nombre) throws ErrorServicio{
        if (nombre.isEmpty()){
            throw new ErrorServicio("Nombre vacío");
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
        System.out.println("Editorial guardada");
    }
}
