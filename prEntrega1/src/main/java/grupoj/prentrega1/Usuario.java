/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.prentrega1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Miguel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column( name = "tipoNotificacionesRecibir", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipoNotificacionesRecibir;
    @Column( name = "email", nullable = false, unique =true)
    private String email;
    @Column( name = "password", nullable = false)
    private String password;
    @Column( name = "borrado", nullable = false)
    private boolean borrado;
    @Column( name = "nombre", nullable = false)
    private String nombre;
    private String apellidos;
    private String multimedia;
    private String telefono;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    // Relacion "tiene" de la entidad Usuario con la entidad geolocalización
    @ManyToOne
    private Geolocalizacion geolocalizacion;
    
    // Relacion "Ver_Buscar" de la entidad Usuario con la entidad Eventos
    @ManyToMany
    @JoinTable(name = "jnd_verBuscarEventos",
            joinColumns = @JoinColumn(name = "usuario_fk"),
            inverseJoinColumns = @JoinColumn(name = "evento_fk"))
    private List<Evento> verBuscarEventos;
    
    // Relacion "Me_Interesa" de la entidad Usuario con la entidad Eventos
    @ManyToMany
    @JoinTable(name = "jnd_meInteresa",
            joinColumns = @JoinColumn(name = "usuario_fk"),
            inverseJoinColumns = @JoinColumn(name = "evento_fk"))
    private List<Evento> meInteresa;

    // Relacion "Recibir" de la entidad Usuario con la entidad Notificacion
    @ManyToMany
    @JoinTable(name = "jnd_notificaciones",
            joinColumns = @JoinColumn(name = "usuario_fk"),
            inverseJoinColumns = @JoinColumn(name = "notificacion_fk"))
    private List<Notificacion> notificaciones;
    
    // Relacion "Subir" de la entidad Usuario con la entidad Evento
    @OneToMany(mappedBy = "subido_by")
    private List<Evento> subidas;

    // Relacion "Dejar" de la entidad Usuario con la entidad Valoracion_eve
    @OneToMany(mappedBy = "realizado_por")
    private List<Valoracion_eve> val_eve;
    
    // Relacion "dejar" de la entidad Usuario con la entidad Valoracion_lug
    @OneToMany(mappedBy = "realizado_por")
    private List<Valoracion_lug> val_lug;
    
    // Relacion "tiene" de la entidad Usuario con la entidad Formulario
    @OneToOne(mappedBy = "usuario")
    private Formulario form;
    
    // Relacion "enviar" de la entidad Usuario con la entidad Mensaje
    @OneToMany(mappedBy = "enviadoPor")
    private List<Mensaje> msg_send;

    public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public void setVal_eve(List<Valoracion_eve> val_eve) {
        this.val_eve = val_eve;
    }

    public void setVal_lug(List<Valoracion_lug> val_lug) {
        this.val_lug = val_lug;
    }

    public void setForm(Formulario form) {
        this.form = form;
    }

    public void setMsg_send(List<Mensaje> msg_send) {
        this.msg_send = msg_send;
    }
    

    
    public List<Evento> getSubidas() {
        return subidas;
    }

    public void setSubidas(List<Evento> subidas) {
        this.subidas = subidas;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Valoracion_eve> getVal_eve() {
        return val_eve;
    }

    public List<Valoracion_lug> getVal_lug() {
        return val_lug;
    }

    public Formulario getForm() {
        return form;
    }

    public List<Mensaje> getMsg_send() {
        return msg_send;
    }
    

    
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    
    
    public Geolocalizacion getGeolocalizacion() {
        return geolocalizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoNotificacion getTipoNotificacionesRecibir() {
        return tipoNotificacionesRecibir;
    }

    public void setTipoNotificacionesRecibir(TipoNotificacion tipoNotificacionesRecibir) {
        this.tipoNotificacionesRecibir = tipoNotificacionesRecibir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
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

    public List<Evento> getVerBuscarEventos() {
        return verBuscarEventos;
    }

    public void setVerBuscarEventos(List<Evento> verBuscarEventos) {
        this.verBuscarEventos = verBuscarEventos;
    }

    public List<Evento> getMeInteresa() {
        return meInteresa;
    }

    public void setMeInteresa(List<Evento> meInteresa) {
        this.meInteresa = meInteresa;
    }


   



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entregajpa.Usuario[ id=" + id + " ]";
    }
    
}
