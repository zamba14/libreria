
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository <Autor,String>{
    @Query("SELECT c FROM Autor c where c.nombre = :nombre")
    public Autor buscarPorNombre(@Param ("nombre") String nombre);
    
}
