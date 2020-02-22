

package com.libreria.libreria.servicios;

import com.libreria.libreria.Errores.ErrorServicio;
import com.libreria.libreria.entidades.Cliente;
import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.entidades.Prestamo;
import com.libreria.libreria.repositorios.ClienteRepositorio;
import com.libreria.libreria.repositorios.LibroRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.libreria.repositorios.PrestamoRepositorio;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ui.ModelMap;

@Service
public class PrestamoServicio {
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private LibroServicio libroServicio;
    public void registrar(Cliente cliente, Libro libro, Date devolucion) throws ErrorServicio{
        if (cliente == null || libro == null || devolucion == null){
            throw new ErrorServicio("Alguno de los campos está vacío");
        }
        
        Prestamo prestamo = new Prestamo();
        prestamo.setCliente(cliente);
        prestamo.setLibro(libro);
        prestamo.setDevolucion(devolucion);
        prestamo.setPrestamo(new Date());
        prestamo.setDevuelto(false);
        prestamoRepositorio.save(prestamo);
        System.out.println("Prestamo guardado");
        
    }
    public boolean devolver(Prestamo prestamo){
        prestamo.setDevuelto(true);
        Libro libro = libroRepositorio.getOne(prestamo.getLibro().getId());
        int cant = libro.getPrestados();
        libro.setPrestados(cant-1);
        prestamoRepositorio.save(prestamo);
        libroRepositorio.save(libro);
        
        return true;
    }
    
    public boolean devolver(String prestamo){
        Optional<Prestamo> prestOpt =prestamoRepositorio.findById(prestamo);
        if(prestOpt.isPresent()){
            Prestamo prest = prestOpt.get();
            return devolver(prest);
        }
        else {return false;
        }
        
    }
    public void registrar(String clienteId, String libroId, String devolucion) throws ErrorServicio{
        if (clienteId == null || libroId == null || devolucion == null){
            throw new ErrorServicio("Alguno de los campos está vacío");
        }
        System.out.println(devolucion);

//        System.out.println(dateFormatter);
        Format dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(dateFormatter1);

        Date fecha1 = null;
        try{

       fecha1 = (Date) dateFormatter1.parseObject(devolucion);
            
            System.out.println(fecha1);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(PrestamoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cliente cliente = clienteRepositorio.getOne(clienteId);
        Libro libro = libroServicio.validarLibro(libroId);
        System.out.println(libro.getTitulo());
        Prestamo prestamo = new Prestamo();
        prestamo.setCliente(cliente);
        prestamo.setLibro(libro);
        prestamo.setDevolucion(fecha1);
        prestamo.setPrestamo(new Date());
        prestamo.setDevuelto(false);
        prestamoRepositorio.save(prestamo);
        System.out.println("Prestamo guardado");
        
        
        
    }
    
    public void devolucionesPorUsuario(ModelMap modelo, Cliente cliente ){
        List<Prestamo> lista = prestamoRepositorio.buscarPorUsuario(cliente);
        modelo.put("prestamos", lista);
        }
    }
    

