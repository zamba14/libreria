
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String>{
   @Query("SELECT c from Cliente c where c.nombre = :nombre")
   public List<Cliente> buscarPorNombre(@Param ("nombre") String nombre);
   @Query("SELECT c from Cliente c where c.apellido = :apellido")
   public List<Cliente> buscarPorApellido(@Param ("apellido") String apellido);
   
    
}
