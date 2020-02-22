
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Cliente;
import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.entidades.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository <Prestamo,String> {
    @Query("SELECT c from Prestamo c WHERE c.cliente = :Cliente AND c.devuelto=false")
    public List<Prestamo> buscarPorUsuario(@Param ("Cliente") Cliente cliente);
    @Query("SELECT c from Prestamo c WHERE c.libro = :Libro AND c.devuelto=false")
    public List<Prestamo> buscarPorLIbro(@Param ("Libro") Libro libro);
    
    
}
