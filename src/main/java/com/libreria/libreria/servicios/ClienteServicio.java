

package com.libreria.libreria.servicios;

import com.libreria.libreria.Errores.ErrorServicio;
import com.libreria.libreria.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.libreria.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    public void registrar(String nombre, String apellido, String telefono, String domicilio) throws ErrorServicio{
       if (nombre.isEmpty()||apellido.isEmpty()||nombre == null || apellido == null){
           throw new ErrorServicio("Alguno de los valores está vacío");
       }
        
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
       cliente.setDomicilio(domicilio);
       cliente.setTelefono(telefono);
        System.out.println(cliente);
        System.out.println("-----------");
       clienteRepositorio.save(cliente);
        System.out.println("Cliente guardado");
    }
}
