
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository <Libro,String> {
    @Query("SELECT c from Libro c where c.titulo = :nombre")
    public Libro buscarPorNombre (@Param("nombre") String nombre);
    @Query("SELECT c from Libro c WHERE c.autor = :Autor")
    public List<Libro> buscarPorAutor (@Param("Autor") Autor autor);
}
