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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anaes
 */
@Named(value = "configurarPerfil")
@RequestScoped
public class configurarPerfil{
    @Inject
    private ControlAutorizacion control;
    
    @Inject
    private PersistenceMock persistencia;
    private List<Usuario> listaUsuario;
    private byte[] foto;
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
        listaUsuario.remove(usuario);
        foto = usuario.getMultimedia();
        nombre = usuario.getNombre();
        apellidos = usuario.getApellidos();
        email = usuario.getEmail();
        contrasenia = usuario.getPassword();
        telefono = usuario.getTelefono();
        fechaNacimiento = usuario.getFechaNacimiento();
                
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(contrasenia);
        usuario.setTelefono(telefono);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setMultimedia(foto);
        listaUsuario.add(usuario);
        try {
            persistencia.setListaUsuarios(listaUsuario);
        } catch (InterruptedException ex) {
            Logger.getLogger(configurarPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "index.html";

    }
    
    
}
