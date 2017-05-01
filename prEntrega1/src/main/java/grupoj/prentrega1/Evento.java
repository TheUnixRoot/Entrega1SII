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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juanp
 */
@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    private boolean borrado;
    @Column(nullable = false)
    private boolean validado;
    private String descripcion;
    private double precio;
    private String donde_comprar;
    
    // Relacion incluye Bidireccional Tag <-> Evento
    @ManyToMany
    @JoinTable(name = "jnd_tag_evento",
            joinColumns = @JoinColumn(name = "tags_FK"),
            inverseJoinColumns = @JoinColumn(name = "evento_FK"))
    private List<Tag> tagged_by;
    
    // Relacion es_gestionado Bidireccional Tag <-> Evento
    @ManyToMany(mappedBy = "")
    private List<Periodista> gestionado_by;
    
    // Relacion ocurre_en Bidireccional Evento <-> Lugar
    @ManyToOne
    @JoinColumn(name="ocurre_en_FK", nullable=false)
    private Lugar ocurre_in;
            
    // Relacion subir Bidireccional Usuario <-> Evento
    @ManyToOne
    @JoinColumn(name="subido_por_FK", nullable=true)
    private Usuario subido_by;
    
    // Relacion recibe Bidireccional Valoracion_eve <-> Evento
    @OneToMany(mappedBy = "valoracion_sobre")
    private List<Valoracion_eve> valoraciones_sobre;
    
    // Relacion ver_buscar Bidireccional Usuario <-> Evento
    @ManyToMany(mappedBy = "verBuscarEventos")
    private List<Usuario> verBus_by;
    
    // Relacion historial_eventos Bidireccional Tag <-> Evento
    @ManyToMany(mappedBy = "historialEventos")
    private List<Formulario> historiado_by;
    
    // Relacion me_interesa Bidireccional Usuario <-> Evento
    @ManyToMany(mappedBy = "meInteresa")
    private List<Usuario> interesados_at;
    
    // ||||||||||| GETTERS Y SETTERS --------------------

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDonde_comprar() {
        return donde_comprar;
    }

    public void setDonde_comprar(String donde_comprar) {
        this.donde_comprar = donde_comprar;
    }

    public List<Tag> getTagged_by() {
        return tagged_by;
    }

    public void setTagged_by(List<Tag> tagged_by) {
        this.tagged_by = tagged_by;
    }

    public List<Periodista> getGestionado_by() {
        return gestionado_by;
    }

    public void setGestionado_by(List<Periodista> gestionado_by) {
        this.gestionado_by = gestionado_by;
    }

    public Lugar getOcurre_in() {
        return ocurre_in;
    }

    public void setOcurre_in(Lugar ocurre_in) {
        this.ocurre_in = ocurre_in;
    }

    public Usuario getSubido_by() {
        return subido_by;
    }

    public void setSubido_by(Usuario subido_by) {
        this.subido_by = subido_by;
    }

    public List<Valoracion_eve> getValoraciones_sobre() {
        return valoraciones_sobre;
    }

    public void setValoraciones_sobre(List<Valoracion_eve> valoraciones_sobre) {
        this.valoraciones_sobre = valoraciones_sobre;
    }

    public List<Usuario> getVerBus_by() {
        return verBus_by;
    }

    public void setVerBus_by(List<Usuario> verBus_by) {
        this.verBus_by = verBus_by;
    }

    public List<Formulario> getHistoriado_by() {
        return historiado_by;
    }

    public void setHistoriado_by(List<Formulario> historiado_by) {
        this.historiado_by = historiado_by;
    }

    public List<Usuario> getInteresados_at() {
        return interesados_at;
    }

    public void setInteresados_at(List<Usuario> interesados_at) {
        this.interesados_at = interesados_at;
    }
    
    
    
    // ^^^^^^^^^^^ GETTERS Y SETTERS --------------------
    
    // Autogen Code -------------------------------------
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoj.entregajpa.Evento[ id=" + id + " ]";
    }
    
}
