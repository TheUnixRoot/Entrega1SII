package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import grupoj.prentrega1.*;
import java.awt.Image;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author anaes
 */
@Named(value = "configurarPerfil")
@RequestScoped
public class configurarPerfil {
    @Inject
    private ControlAutorizacion control;
    
    @Inject
    private PersistenceMock persistencia;
    private List<Usuario> listaUsuario;
    private Image foto;
    private Usuario usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenia;
    private String telefono;
    private Date fechaNacimiento;
    
    /**
     * Creates a new instance of configurarPerfil
     */
    @PostConstruct
    public void init() {
        listaUsuario = persistencia.getListaUsuarios();
        usuario = control.getUsuario();
        //foto = usuario.getMultimedia();
        nombre = usuario.getNombre();
        apellidos = usuario.getApellidos();
        email = usuario.getEmail();
        contrasenia = usuario.getPassword();
        telefono = usuario.getTelefono();
        fechaNacimiento = usuario.getFechaNacimiento();
                
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombreU(){
        return "Pepe";
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String configurar(){
        //usuario.setMultimedia(foto);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(contrasenia);
        usuario.setTelefono(telefono);
        usuario.setFechaNacimiento(fechaNacimiento);
        listaUsuario.remove(0);
        listaUsuario.add(usuario);
        persistencia.setListaUsuarios(listaUsuario);
        
        return "index.html";
    }
    
    
}
