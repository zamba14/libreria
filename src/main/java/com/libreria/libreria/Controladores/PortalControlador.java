package com.libreria.libreria.Controladores;

import com.libreria.libreria.Errores.ErrorServicio;
import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.entidades.Cliente;
import com.libreria.libreria.entidades.Editorial;
import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.repositorios.AutorRepositorio;
import com.libreria.libreria.repositorios.ClienteRepositorio;
import com.libreria.libreria.repositorios.EditorialRepositorio;
import com.libreria.libreria.repositorios.LibroRepositorio;
import com.libreria.libreria.repositorios.PrestamoRepositorio;
import com.libreria.libreria.servicios.AutorServicio;
import com.libreria.libreria.servicios.ClienteServicio;
import com.libreria.libreria.servicios.EditorialServicio;
import com.libreria.libreria.servicios.LibroServicio;
import com.libreria.libreria.servicios.PrestamoServicio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private PrestamoServicio prestamoServicio;
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {

        return "/login.html";
        
    }

    @GetMapping("/prestamo")
    public String prestamo(ModelMap modelo) {
        List <Libro> libros = libroRepositorio.findAll();
        modelo.put("libros",libros);
        List <Cliente> clientes = clienteRepositorio.findAll();
        modelo.put("clientes",clientes);
        return "/prestamo.html";
    }



    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        List <Autor> autor = autorRepositorio.findAll();
        modelo.put("autores",autor);
        List <Editorial> editorial = editorialRepositorio.findAll();
        modelo.put("editoriales",editorial);
        return "/registro.html";
    }

    @GetMapping("/registroCliente")
    public String registroCliente() {
        return "/registroCliente.html";
    }

    @GetMapping("/index")
    public String index1() {
        return "index.html";
    }

    @PostMapping("/registrarCliente")
    public String registrarCliente(ModelMap modelo, @RequestParam String name, @RequestParam String apellido, @RequestParam String phone, @RequestParam String address) {
        System.out.println(name + apellido + phone + address);
        try {
            clienteServicio.registrar(name, apellido, phone, address);
        } catch (ErrorServicio ex) {
            return error(modelo, ex.getMessage());
        }
        return exito(modelo, "Cliente Registrado!");
    }
    
        @PostMapping("/registrarAutor")
    public String registrarAutor(ModelMap modelo, @RequestParam String name) {
        System.out.println(name);
        try {
            autorServicio.registrar(name);
        } catch (ErrorServicio ex) {
            return error(modelo, ex.getMessage());
        }
        return exito(modelo, "Autor registrado!");
    }
    
            @PostMapping("/registrarEditorial")
    public String registrarEditorial(ModelMap modelo, @RequestParam String name) {
        System.out.println(name);
        try {
            editorialServicio.registrar(name);
        } catch (ErrorServicio ex) {
           return error(modelo, ex.getMessage());
        }
        return exito(modelo, "Editorial Registrada!");
    }
    
    @PostMapping("/registrarLibro")
    public String registrarLibro (ModelMap modelo, @RequestParam String titulo, @RequestParam String autorId, @RequestParam String editorialId, @RequestParam int ejemplares){
                System.out.println(titulo);
                System.out.println(autorId);
                System.out.println(editorialId);
                System.out.println(ejemplares);
        try {
            libroServicio.registrar(titulo, 0, ejemplares, autorId, editorialId);
        } catch (ErrorServicio ex) {
            return error(modelo, ex.getMessage());
        }
        return exito(modelo, "Libro registrado!");
    }
    @PostMapping("/prestar")
    public String prestarLibro(ModelMap modelo, @RequestParam String libroId, @RequestParam String clienteId, @RequestParam String date){
        try{
            prestamoServicio.registrar(clienteId, libroId, date);
        }catch (ErrorServicio ex){
            System.out.println(ex.getMessage());
            return error(modelo, ex.getMessage());
        }
        System.out.println("Exito");
        return exito(modelo, "Préstamo generado!");
    }
    
        @GetMapping("/exito")
    public String exito(ModelMap modelo, String mensaje) {
        modelo.put("mensajes",mensaje);
        return "/exito.html";
    }
    
            @GetMapping("/exito2")
    public String exito2(ModelMap modelo, String mensaje) {
        modelo.put("mensajes",mensaje);
        return "/exito2.html";
    }
    
            @GetMapping("/error")
    public String error(ModelMap modelo, String mensaje) {
        modelo.put("mensajes",mensaje);
        return "/error.html";
    }
    
    @GetMapping("/devolucion")
    public String devolucion(ModelMap modelo){
        List <Cliente> clientes = clienteRepositorio.findAll();
        modelo.put("clientes",clientes);
        return "/devolucion.html";
    }
    
    @PostMapping("/getPrestamos")
    public String prepararDevoluciones(ModelMap modelo, @RequestParam String clienteId){

        
        
        Optional <Cliente> clienteOpt = clienteRepositorio.findById(clienteId);
        if (clienteOpt.isPresent()){
            Cliente cliente = clienteOpt.get();
            prestamoServicio.devolucionesPorUsuario(modelo, cliente);
            modelo.put("cliente", cliente);
            return "/devolucion2.html";
        }
        else {
            return error(modelo, "Ha habido un Error");
        }
        
        
        
    }
    
    @PostMapping("/devolver")
        public String devolver(ModelMap modelo, @RequestParam String prestamoId){
       
            if(prestamoServicio.devolver(prestamoId)){
                return exito2(modelo, "Devuelto!");
            } else {
                return error(modelo, "No se pudo devolver");
            }
            
}
    
    
    
    }


