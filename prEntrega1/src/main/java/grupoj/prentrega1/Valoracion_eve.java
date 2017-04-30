/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.prentrega1;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author anaes
 */
@Entity
public class Valoracion_eve implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private int calificacion;
    private String comentario;
    private String fotos;
    
    // Relacion Bidireccional Usuario <-> Valoracion_eve
    @ManyToOne
    @JoinColumn(name="realizado_por_fk", nullable=false)
    private Usuario realizado_por;
 
    // Relacion Bidireccional Evento <-> Valoracion_eve
    @ManyToOne
    @JoinColumn(name="valoracion_sobre_fk", nullable=false)
    private Evento valoracion_sobre;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Usuario getRealizado_por() {
        return realizado_por;
    }

    public Evento getValoracion_sobre() {
        return valoracion_sobre;
    }

    public void setRealizado_por(Usuario realizado_por) {
        this.realizado_por = realizado_por;
    }

    public void setValoracion_sobre(Evento valoracion_sobre) {
        this.valoracion_sobre = valoracion_sobre;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
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
        if (!(object instanceof Valoracion_eve)) {
            return false;
        }
        Valoracion_eve other = (Valoracion_eve) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoj.prentrega1.Valoracion_eve[ id=" + id + " ]";
    }
    
}
