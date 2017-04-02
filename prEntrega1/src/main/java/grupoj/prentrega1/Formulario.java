/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.prentrega1;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Miguel
 */
@Entity
public class Formulario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Relacion "tiene pertenece" de la entidad Formulario con la entidad usuario
    @OneToOne
    @JoinColumn(name = "usuario_fk" , nullable = false)
    private Usuario usuario;
    
    // Relacion "historial eventos" de la entidad Formulario con la entidad Eventos
    @ManyToMany
    @JoinTable(name = "jnd_historial_eventos",
            joinColumns = @JoinColumn(name = "formulario_fk"),
            inverseJoinColumns = @JoinColumn(name = "evento_fk"))
    private List<Evento> historialEventos;
    
    // Relacion "incluye" de la entidad Formulario con la entidad Tag
    @OneToMany(mappedBy = "form")
    private List<Tag> form_tags;
    
    // Relacion "sobre" de la entidad Formulario con la entidad Notificacion
    @ManyToMany(mappedBy = "sobre_src")
    private List<Notificacion> sobre_by;
    
    public List<Evento> getHistorialEventos() {
        return historialEventos;
    }

    public void setHistorialEventos(List<Evento> historialEventos) {
        this.historialEventos = historialEventos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Formulario)) {
            return false;
        }
        Formulario other = (Formulario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoj.prentrega1.Formulario[ id=" + id + " ]";
    }
    
}
